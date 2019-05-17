package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.*;
import edu.mum.tmcheck.serviceimp.BlockServiceImp;
import edu.mum.tmcheck.serviceimp.CourseServiceImp;
import edu.mum.tmcheck.serviceimp.FacultyServiceImp;
import edu.mum.tmcheck.serviceimp.OfferedCourseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private List<Block> blocks = new ArrayList<>();
    private List<Faculty> facultyUsers = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    @Override
    public void generate(int size) {
        blocks = blockServiceImp.findAll();
        courses = courseServiceImp.findAll();
        facultyUsers = facultyServiceImp.findAll();

        while (size-- > 0) {
            OfferedCourse offeredCourse = new OfferedCourse();

            offeredCourse.setBlock(randomBlock());
            offeredCourse.setFaculty(randomFacultyUser());
            offeredCourse.setCourse(randomCourse());

            offeredCourseServiceImp.save(offeredCourse);
        }
    }

    public Course randomCourse(){
        int index = random.nextInt(courses.size()-1)+1;

        return courses.get(index);
    }

    public Block randomBlock(){
        int index = random.nextInt(courses.size()-1)+1;

        return blocks.get(index);
    }

    public Faculty randomFacultyUser(){
        int index = random.nextInt(facultyUsers.size()-1)+1;

        return facultyUsers.get(index);
    }
}
