package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Long> {
}
