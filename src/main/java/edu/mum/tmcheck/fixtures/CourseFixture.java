package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Course;
import edu.mum.tmcheck.domain.repository.CourseRepository;
import edu.mum.tmcheck.serviceimp.CourseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CourseFixture extends BaseFixture{
    @Autowired
    CourseServiceImp courseServiceImp;

    @Override
    public void generate(int size) {
        while (size-- > 0){
            Course course = new Course();
            course.setName(faker.educator().course());
            course.setCode(faker.bothify("CS###", true));

            courseServiceImp.save(course);
        }
    }

    public List<Course> randomCourses(int count){
        Set<Long> courseIds = new HashSet<>();
        Random random = new Random();

        while (courseIds.size() < count){
            int id = random.nextInt(count);
            courseIds.add(Long.valueOf(id));
        }

        return courseServiceImp.findAllById(new ArrayList<Long>(courseIds));
    }
}
