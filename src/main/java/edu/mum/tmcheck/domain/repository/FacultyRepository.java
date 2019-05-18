package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Long> {
    public Faculty getTopByIdIsTrue();

    @Query(value = "SELECT * FROM USER WHERE DTYPE = 'Faculty' AND true LIMIT 1", nativeQuery = true)
    public Faculty getFirst();
}
