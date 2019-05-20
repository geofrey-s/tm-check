package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Location;

import java.util.List;

public interface LocationService {
    public void create();

    public Location get();

    public Location save(Location location);

    public List<Location> findAll();

    public Location findByName(String name);
}
