package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.MeditationType;
import edu.mum.tmcheck.domain.repository.MeditationTypeRepository;
import edu.mum.tmcheck.services.MeditationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeditationTypeServiceImp implements MeditationTypeService {
    @Autowired
    MeditationTypeRepository meditationTypeRepository;

    public static String fromShortCode(String code) {
        switch (code.toUpperCase()) {
            case "DC":
                return MeditationType.TMCHECK;
            case "RT":
                return MeditationType.RETREAT;
            default: // AM/EAM
                return MeditationType.STANDARD;
        }
    }

    public MeditationType findByName(String name) {
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

    @Override
    public List<MeditationType> findAll() {
        List<MeditationType> records = new ArrayList<>();

        meditationTypeRepository.findAll().forEach(records::add);
        return records;
    }

    @Override
    public MeditationType findById(Long id) {
        return meditationTypeRepository.findById(id).get();
    }
}
