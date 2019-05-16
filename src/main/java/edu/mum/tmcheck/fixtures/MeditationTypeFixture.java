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

        meditationType.setName("EAM");
        meditationType.setStartTime(LocalTime.of(8, 0));
        meditationType.setEndTime(LocalTime.of(8, 30));

        meditationTypeServiceImp.save(meditationType);

        meditationType = new MeditationType();

        meditationType.setName("AM");
        meditationType.setStartTime(LocalTime.of(8, 45));
        meditationType.setEndTime(LocalTime.of(9, 10));

        meditationTypeServiceImp.save(meditationType);

        meditationType = new MeditationType();

        meditationType.setName("DC");
        meditationType.setStartTime(LocalTime.of(12, 0));
        meditationType.setEndTime(LocalTime.of(13, 30));

        meditationTypeServiceImp.save(meditationType);
    }
}
