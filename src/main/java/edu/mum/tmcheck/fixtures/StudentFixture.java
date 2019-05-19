package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Entry;
import edu.mum.tmcheck.domain.entities.OfferedCourse;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.serviceimp.EntryServiceImp;
import edu.mum.tmcheck.serviceimp.OfferedCourseServiceImp;
import edu.mum.tmcheck.serviceimp.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentFixture extends BaseFixture {
    @Autowired
    StudentServiceImp studentServiceImp;

    @Autowired
    EntryServiceImp entryServiceImp;

    @Autowired
    OfferedCourseServiceImp offeredCourseServiceImp;

    List<OfferedCourse> offeredCourses = new ArrayList<>();

    @Override
    public void generate(int size) {
        offeredCourses = offeredCourseServiceImp.findAll();

        while (size-- > 0) {
            Student student = new Student();
            student.setRole("student");
            student.setName(faker.name().fullName());
            student.setDepartureDate(futureDateByYear(1));

            String username = faker.name().username();
            student.setUsername(username);
            student.setPassword(username);

            student.setStudentRegId(faker.bothify("000-9#-####"));

            Entry entry = entryServiceImp.findById(random.nextInt(2) + 1);
            student.setEntry(entry);
            student = studentServiceImp.save(student);

            student.setEnrolledCourses(randomEnrolledCourses());
            studentServiceImp.save(student);
        }
    }

    public List<OfferedCourse> randomEnrolledCourses() {
        int numCourse = 4;

        List<OfferedCourse> courses = new ArrayList<>();

        while (numCourse-- > 0) {
            int index = random.nextInt(offeredCourses.size() - 1) + 1;
            courses.add(offeredCourses.get(index));
        }

        return courses;
    }
}
