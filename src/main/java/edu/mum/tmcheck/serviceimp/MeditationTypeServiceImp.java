package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.MeditationType;
import edu.mum.tmcheck.domain.repository.MeditationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeditationTypeServiceImp {
    @Autowired
    MeditationTypeRepository meditationTypeRepository;

    public MeditationType findByName(String name){
        return meditationTypeRepository.findByName(name);
    }
}
