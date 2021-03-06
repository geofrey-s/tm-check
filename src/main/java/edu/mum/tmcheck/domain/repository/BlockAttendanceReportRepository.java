package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlockAttendanceReportRepository extends CrudRepository<BlockAttendanceReport, String> {
//    @Query(value = "SELECT b FROM BlockAttendanceReport AS b WHERE b.blockStart = :blockStart and b.blockEnd = :blockEnd")
//    public List<BlockAttendanceReport> findByBlock(LocalDate blockStart, LocalDate blockEnd);

    public BlockAttendanceReport findByStudentRegIdAndBlockId(String studentRegId, long blockId);

    public List<BlockAttendanceReport> findAllById(long id);

    public List<BlockAttendanceReport> findAllByStudentRegIdOrderByBlockStartDesc(String studentRegId);
}
