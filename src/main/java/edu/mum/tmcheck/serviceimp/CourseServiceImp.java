package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Course;
import edu.mum.tmcheck.domain.repository.CourseRepository;
import edu.mum.tmcheck.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void create() {
    }

    @Override
    public Course get() {
        return null;
    }

    @Override
    public Course save(Course instance) {
        return courseRepository.save(instance);
    }

    @Override
    public List<Course> findAllById(Collection<Long> ids) {
        return courseRepository.findAllByIdIn(ids);
    }

    public Course findById(long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course getFirst() {
        return courseRepository.getFirst();
    }
}
