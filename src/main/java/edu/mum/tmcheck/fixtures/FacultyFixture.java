package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Faculty;
import edu.mum.tmcheck.serviceimp.FacultyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacultyFixture extends BaseFixture {
    private final String DEFAULT_USERNAME = "faculty";

    @Autowired
    FacultyServiceImp facultyServiceImp;

    @Override
    public void generate(int size) {
        String username = DEFAULT_USERNAME;

        while (size-- > 0) {
            try {
                Faculty faculty = new Faculty();
                faculty.setRole("faculty");
                faculty.setName(faker.name().fullName());

                faculty.setUsername(username);
                faculty.setPassword(username);
                username = faker.name().username();

                facultyServiceImp.save(faculty);
            } catch (Exception e) {
                size++;
            }
        }
    }
}
