package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    public List<Course> findAllByIdIn(Collection<Long> ids);
    public Course getTopByIdIsTrue();

    @Query(value = "SELECT * FROM course WHERE true LIMIT 1", nativeQuery = true)
    public Course getFirst();

}
