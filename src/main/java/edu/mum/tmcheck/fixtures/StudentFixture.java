package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Entry;
import edu.mum.tmcheck.domain.entities.Student;
import edu.mum.tmcheck.serviceimp.EntryServiceImp;
import edu.mum.tmcheck.serviceimp.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentFixture extends BaseFixture{
    @Autowired
    StudentServiceImp studentServiceImp;

    @Autowired
    EntryServiceImp entryServiceImp;

    @Override
    public void generate(int size) {
        while (size-- > 0){
            Student student = new Student();
            student.setName(faker.name().fullName());
            student.setDepartureDate(futureDateByYear(1));

            String username = faker.name().username();
            student.setUsername(username);
            student.setPassword(username);

            student.setStudentRegId(faker.bothify("000-9#-####"));

            Entry entry = entryServiceImp.findById(random.nextInt(2) + 1);
            student.setEntry(entry);

            studentServiceImp.save(student);
        }
    }
}
