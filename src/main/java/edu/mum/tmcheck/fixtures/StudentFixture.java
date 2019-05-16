package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.serviceimp.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentFixture extends BaseFixture{
    @Autowired
    StudentServiceImp studentServiceImp;

    @Override
    public void generate(int size) {
        while (size-- > 0){
            Student student = new Student();
            student.setName(faker.name().fullName());
            student.setDepartureDate(futureDateByYear(1));

            String username = faker.name().username();
            student.setUsername(username);
            student.setPassword(username);

        }
    }
}
