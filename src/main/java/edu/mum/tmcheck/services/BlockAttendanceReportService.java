package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;

import java.time.LocalDate;
import java.util.List;

public interface BlockAttendanceReportService {
//    public List<BlockAttendanceReport> findByBlock(LocalDate block_start, LocalDate block_end);

    public List<BlockAttendanceReport> findAllByStudentRegIdOrderByBlockStartDesc(String studentRegId);
    public BlockAttendanceReport findByStudentRegIdAndBlockId(String studentRegId, long blockId);
}
