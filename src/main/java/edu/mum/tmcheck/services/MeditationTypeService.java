package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.MeditationType;

public interface MeditationTypeService {
    public void create();

    //    public void update();
    public MeditationType get();
    public MeditationType save(MeditationType meditationType);
}
