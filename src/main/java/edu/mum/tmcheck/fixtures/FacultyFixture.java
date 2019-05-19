package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Faculty;
import edu.mum.tmcheck.serviceimp.FacultyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacultyFixture extends BaseFixture {
    @Autowired
    FacultyServiceImp facultyServiceImp;

    @Override
    public void generate(int size) {
        while (size-- > 0) {
            Faculty faculty = new Faculty();
            faculty.setRole("faculty");
            faculty.setName(faker.name().fullName());

            String username = faker.name().username();
            faculty.setUsername(username);
            faculty.setPassword(username);

            facultyServiceImp.save(faculty);
        }
    }
}
