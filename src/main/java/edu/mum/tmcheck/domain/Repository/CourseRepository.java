package edu.mum.tmcheck.domain.Repository;

import edu.mum.tmcheck.domain.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
