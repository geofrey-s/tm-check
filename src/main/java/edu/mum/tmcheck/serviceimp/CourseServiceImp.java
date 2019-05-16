package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Course;
import edu.mum.tmcheck.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    CourseServiceImp courseServiceImp;

    @Override
    public void create() {

    }

    @Override
    public Course get() {
        return null;
    }

    @Override
    public Course save(Course instance) {
        return courseServiceImp.save(instance);
    }
}
