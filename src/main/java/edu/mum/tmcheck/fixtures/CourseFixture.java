package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Course;
import edu.mum.tmcheck.domain.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseFixture extends BaseFixture{
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void generate(int size) {
        while (size-- > 0){
            Course course = new Course();
            course.setName(faker.educator().course());
            course.setCode(faker.bothify("CS###", true));

            courseRepository.save(course);
        }
    }
}
