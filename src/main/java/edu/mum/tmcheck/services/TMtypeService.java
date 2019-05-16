package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.TMType;

import java.util.List;

public interface TMtypeService {
    public void create();

    //    public void update();
    public TMType get();
    public TMType save(TMType tmType);
    public List<TMType> findAll();
}
