package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
}
