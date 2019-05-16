package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.TMType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TMtypeRepository extends CrudRepository<TMType, Long> {
    public TMType findByName(String code);
}
