package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;

import java.time.LocalDate;
import java.util.List;

public interface BlockAttendanceReportService {
    public List<BlockAttendanceReport> findByBlock(LocalDate block_start, LocalDate block_end);
    public List<BlockAttendanceReport> findByBlockAndStudent(LocalDate block_start, LocalDate block_end, Long studentId);
}
