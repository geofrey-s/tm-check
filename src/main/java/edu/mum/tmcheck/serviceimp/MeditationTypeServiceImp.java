package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.MeditationType;
import edu.mum.tmcheck.domain.repository.MeditationTypeRepository;
import edu.mum.tmcheck.services.MeditationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeditationTypeServiceImp implements MeditationTypeService {
    @Autowired
    MeditationTypeRepository meditationTypeRepository;

    public MeditationType findByName(String name){
        return meditationTypeRepository.findByName(name);
    }

    @Override
    public void create() {

    }

    @Override
    public MeditationType get() {
        return null;
    }

    @Override
    public MeditationType save(MeditationType meditationType) {
        return meditationTypeRepository.save(meditationType);
    }
}
