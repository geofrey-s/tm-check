package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Faculty;

public interface FacultyService {
    public void create();

    //    public void update();
    public Faculty get();
    public Faculty save(Faculty faculty);
    public Faculty getFirst();
}
