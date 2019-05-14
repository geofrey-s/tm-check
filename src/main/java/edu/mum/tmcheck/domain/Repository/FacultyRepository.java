package edu.mum.tmcheck.domain.Repository;

import edu.mum.tmcheck.domain.entities.Faculty;
import org.springframework.data.repository.CrudRepository;

public interface FacultyRepository extends CrudRepository<Faculty, Long> {
}
