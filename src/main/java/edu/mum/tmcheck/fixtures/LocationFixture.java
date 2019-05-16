package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Location;
import edu.mum.tmcheck.serviceimp.LocationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationFixture extends BaseFixture {
    @Autowired
    LocationServiceImp locationServiceImp;

    @Override
    public void generate(int size) {
        Location location = new Location();
        location.setName("Dalby Hall");
        location.setCode("DB");

        locationServiceImp.save(location);

        location = new Location();
        location.setName("Hilden Hall");
        location.setCode("HH");

        locationServiceImp.save(location);

        location = new Location();
        location.setName("Art Centre");
        location.setCode("AC");

        locationServiceImp.save(location);

        location = new Location();
        location.setName("Men's Dome");
        location.setCode("MD");

        locationServiceImp.save(location);
    }
}
