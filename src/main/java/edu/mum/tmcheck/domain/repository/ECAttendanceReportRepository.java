package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;
import edu.mum.tmcheck.domain.reports.ECAttendanceReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ECAttendanceReportRepository extends CrudRepository<ECAttendanceReport, String> {
    public List<ECAttendanceReport> findAllByBlockId(long id);
    public List<ECAttendanceReport> findAllByFacultyIdAndBlockId(long facultyId, long blockId);
}
