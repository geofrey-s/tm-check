package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlockAttendanceReportRepository extends CrudRepository<BlockAttendanceReport, String> {
    @Query(value = "SELECT b FROM BlockAttendanceReport AS b WHERE b.block_start = :block_start and b.block_end = :block_end")
    public List<BlockAttendanceReport> findByBlock(LocalDate block_start, LocalDate block_end);


    @Query(value = "SELECT b FROM BlockAttendanceReport AS b WHERE b.block_start = :block_start and b.block_end = :block_end AND b.studentId = :studentId")
    public List<BlockAttendanceReport> findByBlockAndStudent(LocalDate block_start, LocalDate block_end, Long studentId);
}
