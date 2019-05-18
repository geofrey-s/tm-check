package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.MeditationType;

import java.util.List;

public interface MeditationTypeService {
    public void create();

    //    public void update();
    public MeditationType get();

    public MeditationType save(MeditationType meditationType);

    public List<MeditationType> findAll();

}
