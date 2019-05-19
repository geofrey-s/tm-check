package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
    public Location findByCode(String code);
    public Location findByName(String name);
}
