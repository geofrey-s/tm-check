package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;
import edu.mum.tmcheck.domain.reports.ECAttendanceReport;

import java.util.List;

public interface ECAttendanceReportService {
    public List<ECAttendanceReport> findAllByBlockId(long id);
    public List<ECAttendanceReport> findAllByFacultyIdAndBlockId(long facultyId, long blockId);
}
