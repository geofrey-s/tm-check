package edu.mum.tmcheck.domain.Repository;

import edu.mum.tmcheck.domain.entities.MeditationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeditationTypeRepository extends CrudRepository<MeditationType, Long> {

}
