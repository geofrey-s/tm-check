package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Entry;
import edu.mum.tmcheck.domain.repository.EntryRepository;
import edu.mum.tmcheck.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryServiceImp implements EntryService {
    @Autowired
    EntryRepository entryRepository;

    @Override
    public void create() {

    }

    @Override
    public Entry get() {
        return null;
    }

    public Entry save(Entry instance){
        return entryRepository.save(instance);
    }

    public Entry findById(long id){
        return entryRepository.findById(id).get();
    }

    @Override
    public List<Entry> findAll() {
        List<Entry> records = new ArrayList<>();

        entryRepository.findAll().forEach(records::add);

        return records;
    }

}
