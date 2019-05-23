package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.*;
import edu.mum.tmcheck.serviceimp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StudentFixture extends BaseFixture {
    public static final String DEFAULT_USERNAME = "student";

    @Autowired
    StudentServiceImp studentServiceImp;

    @Autowired
    EntryServiceImp entryServiceImp;

    @Autowired
    OfferedCourseServiceImp offeredCourseServiceImp;

    @Autowired
    IdCardServiceImp idCardServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    List<OfferedCourse> offeredCourses = new ArrayList<>();

    @Override
    public void generate(int size) {
        System.out.println("Generating Student fixture data ...");

        offeredCourses = offeredCourseServiceImp.findAll();
        Stack<Card> cards = new Stack<>();
        cards.addAll(idCardServiceImp.findAll());

        String username = DEFAULT_USERNAME;

        while (size-- > 0) {
            try {
                Student student = new Student();
                student.setRole("student");
                student.setName(faker.name().fullName());
                student.setDepartureDate(futureDateByYear(1));

                student.setUsername(username);
                student.setPassword(username);

                student.setStudentRegId(faker.bothify("000-9#-####"));

                Entry entry = entryServiceImp.findById(random.nextInt(2) + 1);
                student.setEntry(entry);
                student = studentServiceImp.save(student);


                if (username == DEFAULT_USERNAME)
                    ensureTaughtByDefaultFaculty(student);
                else
                    student.setEnrolledCourses(randomEnrolledCourses());

                studentServiceImp.save(student);

                Card card = cards.pop();
                card.setStudent(student);
                idCardServiceImp.save(card);

                username = faker.name().username();
            } catch (Exception e) {
                size++;
            }
        }
    }

    public List<OfferedCourse> randomEnrolledCourses() {
        int numCourse = 4;

        List<OfferedCourse> courses = new ArrayList<>();

        while (numCourse-- > 0) {
            int index = random.nextInt(offeredCourses.size() - 1);
            courses.add(offeredCourses.get(index));
        }

        return courses;
    }

    public void ensureTaughtByDefaultFaculty(Student student){
        Faculty defaultFaculty = (Faculty)userServiceImp.findUserByUserName(FacultyFixture.DEFAULT_USERNAME);
        List<OfferedCourse> facultyOfferedCourses = offeredCourseServiceImp.findAllByFacultyId(defaultFaculty.getId());
        List<OfferedCourse> randomOfferedCourses = randomEnrolledCourses();
        Set<OfferedCourse> uniqueOfferedCourses = new HashSet<OfferedCourse>(){{
            add(facultyOfferedCourses.get(random.nextInt(facultyOfferedCourses.size())));
        }};

        randomOfferedCourses.stream().forEach(uniqueOfferedCourses::add);

        student.setEnrolledCourses(new ArrayList<OfferedCourse>(uniqueOfferedCourses));
    }
}
