package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.TMType;
import edu.mum.tmcheck.domain.repository.TMtypeRepository;
import edu.mum.tmcheck.services.TMtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TMtypeServiceImp implements TMtypeService {
    @Autowired
    TMtypeRepository tMtypeRepository;

    public TMType findByName(String code) {
        return tMtypeRepository.findByName(code);
    }

    @Override
    public void create() {

    }

    @Override
    public TMType get() {
        return null;
    }

    public TMType save(TMType tmType) {
        return tMtypeRepository.save(tmType);
    }

    @Override
    public List<TMType> findAll() {
        List<TMType> records = new ArrayList<>();

        tMtypeRepository.findAll().forEach(records::add);
        return records;
    }
}
