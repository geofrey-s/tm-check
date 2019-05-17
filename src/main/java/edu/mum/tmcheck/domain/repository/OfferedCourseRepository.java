package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.OfferedCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferedCourseRepository extends CrudRepository<OfferedCourse, Long> {
    public List<OfferedCourse> findAll();
}
