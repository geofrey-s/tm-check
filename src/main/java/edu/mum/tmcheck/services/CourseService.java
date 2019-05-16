package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Course;

public interface CourseService {
    public void create();

    //    public void update();
    public Course get();
    public Course save(Course instance);
}
