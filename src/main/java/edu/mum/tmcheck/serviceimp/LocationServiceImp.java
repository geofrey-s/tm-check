package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Location;
import edu.mum.tmcheck.domain.repository.LocationRepository;
import edu.mum.tmcheck.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImp implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    public Location findByCode(String code){
        return locationRepository.findByCode(code);
    }

    @Override
    public void create() {

    }

    @Override
    public Location get() {
        return null;
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }
}
