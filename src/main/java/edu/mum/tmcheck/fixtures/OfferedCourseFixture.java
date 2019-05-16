package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.OfferedCourse;
import edu.mum.tmcheck.serviceimp.BlockServiceImp;
import edu.mum.tmcheck.serviceimp.OfferedCourseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

public class OfferedCourseFixture extends BaseFixture{
    @Autowired
    BlockServiceImp blockServiceImp;

    @Autowired
    OfferedCourseServiceImp offeredCourseServiceImp;

    @Override
    public void generate(int size) {
        while (size-- > 0){
            OfferedCourse offeredCourse = new OfferedCourse();
//            offeredCourse.setBlock();

            offeredCourseServiceImp.save(offeredCourse);
        }
    }
}
