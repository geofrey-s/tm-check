package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Entry;

import java.util.List;

public interface EntryService {
    public void create();

    //    public void update();
    public Entry get();

    public Entry save(Entry instance);

    public Entry findById(long id);
    public List<Entry> findAll();
}
