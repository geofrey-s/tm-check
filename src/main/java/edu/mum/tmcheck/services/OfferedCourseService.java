package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.entities.OfferedCourse;

import java.util.List;

public interface OfferedCourseService {
    public void create();

    //    public void update();
    public OfferedCourse get();

    public OfferedCourse save(OfferedCourse instance);

    public List<OfferedCourse> findAll();

    public List<Block> getfacultytaughtblockids(Long userid);
}
