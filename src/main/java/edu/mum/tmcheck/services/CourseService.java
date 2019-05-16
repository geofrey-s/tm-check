package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Course;

import java.util.Collection;
import java.util.List;

public interface CourseService {
    public void create();

    //    public void update();
    public Course get();

    public Course save(Course instance);

    public List<Course> findAllById(Collection<Long> ids);

    public Course findById(long id);
    public Course getFirst();
}
