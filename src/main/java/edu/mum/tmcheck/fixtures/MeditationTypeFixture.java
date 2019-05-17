package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.MeditationType;
import edu.mum.tmcheck.serviceimp.MeditationTypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class MeditationTypeFixture extends BaseFixture{
    @Autowired
    MeditationTypeServiceImp meditationTypeServiceImp;

    @Override
    public void generate(int size) {
        MeditationType meditationType = new MeditationType();
        meditationType.setName("standard");
        meditationTypeServiceImp.save(meditationType);

        meditationType = new MeditationType();
        meditationType.setName("retreat");
        meditationTypeServiceImp.save(meditationType);

        meditationType = new MeditationType();
        meditationType.setName("check");
        meditationTypeServiceImp.save(meditationType);
    }
}
