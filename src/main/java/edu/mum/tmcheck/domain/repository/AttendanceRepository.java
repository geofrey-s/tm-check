package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.domain.entities.MeditationType;
import edu.mum.tmcheck.domain.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    public List<Attendance> findByStudent(Student student);

    public List<Attendance> findAttendancesByStudentAndCreatedAtAfterAndCreatedAtAfter(Student student, LocalDate block_start, LocalDate block_end);

    public List<Attendance> findAllByMeditationTypeNotOrderById(MeditationType meditationType);
}
