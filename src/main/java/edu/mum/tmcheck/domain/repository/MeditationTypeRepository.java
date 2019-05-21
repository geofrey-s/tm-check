package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.MeditationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeditationTypeRepository extends CrudRepository<MeditationType, Long> {
    public MeditationType findByName(String name);

    public List<MeditationType> findAllByNameNot(String name);
}
