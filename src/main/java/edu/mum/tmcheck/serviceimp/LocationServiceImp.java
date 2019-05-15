package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Location;
import edu.mum.tmcheck.domain.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class LocationServiceImp {
    @Autowired
    LocationRepository locationRepository;

    public Location findByCode(String code){
        return locationRepository.findByCode(code);
    }
}
