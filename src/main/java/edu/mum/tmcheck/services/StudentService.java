package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Student;

import java.util.List;

public interface StudentService {
    public void create();

    public Student get();

    public Student save(Student student);

    public List<Student> findAll();

    public Student findByID(Long id);

    public Student findByUsername(String username);
}
