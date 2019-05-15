package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.TMType;
import edu.mum.tmcheck.domain.repository.TMtypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class TMtypeServiceImp {
    @Autowired
    TMtypeRepository tMtypeRepository;

    public TMType findByName(String code){
        return tMtypeRepository.findByName(code);
    }
}
