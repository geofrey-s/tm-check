package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Card;
import edu.mum.tmcheck.domain.entities.Entry;
import edu.mum.tmcheck.domain.entities.OfferedCourse;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.serviceimp.EntryServiceImp;
import edu.mum.tmcheck.serviceimp.IdCardServiceImp;
import edu.mum.tmcheck.serviceimp.OfferedCourseServiceImp;
import edu.mum.tmcheck.serviceimp.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Component
public class StudentFixture extends BaseFixture {
    private final String DEFAULT_USERNAME = "student";

    @Autowired
    StudentServiceImp studentServiceImp;

    @Autowired
    EntryServiceImp entryServiceImp;

    @Autowired
    OfferedCourseServiceImp offeredCourseServiceImp;

    @Autowired
    IdCardServiceImp idCardServiceImp;

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
                username = faker.name().username();

                student.setStudentRegId(faker.bothify("000-9#-####"));

                Entry entry = entryServiceImp.findById(random.nextInt(2) + 1);
                student.setEntry(entry);
                student = studentServiceImp.save(student);

                student.setEnrolledCourses(randomEnrolledCourses());
                studentServiceImp.save(student);

                Card card = cards.pop();
                card.setStudent(student);
                idCardServiceImp.save(card);
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
}
