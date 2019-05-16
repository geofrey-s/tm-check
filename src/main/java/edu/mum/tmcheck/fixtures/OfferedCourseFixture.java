package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.OfferedCourse;
import edu.mum.tmcheck.serviceimp.BlockServiceImp;
import edu.mum.tmcheck.serviceimp.CourseServiceImp;
import edu.mum.tmcheck.serviceimp.FacultyServiceImp;
import edu.mum.tmcheck.serviceimp.OfferedCourseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public void generate(int size) {
        Random random = new Random();

//        OfferedCourse offeredCourse = new OfferedCourse();
//
//        offeredCourse.setBlock(blockServiceImp.getFirst());
//        offeredCourse.setFaculty(facultyServiceImp.getFirst());
//        offeredCourse.setCourse(courseServiceImp.getFirst());
//
//        offeredCourseServiceImp.save(offeredCourse);

        while (size-- > 0) {
            OfferedCourse offeredCourse = new OfferedCourse();

            int blockId = random.nextInt(size - 1) + 1;
            offeredCourse.setBlock(blockServiceImp.findById(blockId));

            int facultyId = random.nextInt(size - 1) + 1;
            offeredCourse.setFaculty(facultyServiceImp.findById(facultyId));

            int courseId = random.nextInt(size - 1) + 1;
            offeredCourse.setCourse(courseServiceImp.findById(courseId));

            offeredCourseServiceImp.save(offeredCourse);
        }
    }
}
