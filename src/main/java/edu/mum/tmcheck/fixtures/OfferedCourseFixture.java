package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.*;
import edu.mum.tmcheck.serviceimp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OfferedCourseFixture extends BaseFixture {
    @Autowired
    BlockServiceImp blockServiceImp;

    @Autowired
    OfferedCourseServiceImp offeredCourseServiceImp;

    @Autowired
    FacultyServiceImp facultyServiceImp;

    @Autowired
    CourseServiceImp courseServiceImp;

    @Autowired
    UserServiceImp userServiceImp;

    private List<Block> blocks = new ArrayList<>();
    private List<Faculty> facultyUsers = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    @Override
    public void generate(int size) {
        System.out.println("Generating Offered Courses fixture data ...");

        blocks = blockServiceImp.findAll();
        courses = courseServiceImp.findAll();
        facultyUsers = facultyServiceImp.findAll();

        ensureFacultyMemmber();

        while (size-- > 0) {
            try {
                OfferedCourse offeredCourse = new OfferedCourse();

                offeredCourse.setBlock(randomBlock());
                offeredCourse.setFaculty(randomFacultyUser());
                offeredCourse.setCourse(randomCourse());

                offeredCourseServiceImp.save(offeredCourse);
            } catch (Exception e) {
                size++;
            }

        }
    }

    public Course randomCourse() {
        int index = random.nextInt(courses.size() - 1);

        return courses.get(index);
    }

    public Block randomBlock() {
        int index = random.nextInt(courses.size() - 1);

        return blocks.get(index);
    }

    public Faculty randomFacultyUser() {
        int index = random.nextInt(facultyUsers.size() - 1);

        return facultyUsers.get(index);
    }

    public void ensureFacultyMemmber(){
        Faculty defaultFaculty = (Faculty)userServiceImp.findUserByUserName(FacultyFixture.DEFAULT_USERNAME);

        for (int i=0; i < 6; i++){
            try {
                OfferedCourse offeredCourse = new OfferedCourse();

                offeredCourse.setBlock(blocks.get(i));
                offeredCourse.setFaculty(defaultFaculty);
                offeredCourse.setCourse(courses.get(i));

                offeredCourseServiceImp.save(offeredCourse);
            } catch (Exception e) {
                i--;
            }
        }
    }
}
