package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.OfferedCourse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferedCourseRepository extends CrudRepository<OfferedCourse, Long> {
}
