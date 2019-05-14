package edu.mum.tmcheck.domain.Repository;

import edu.mum.tmcheck.domain.entities.Attendence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendence, Long> {
}
