package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.reports.BlockAttendanceReport;
import edu.mum.tmcheck.domain.repository.BlockAttendanceReportRepository;
import edu.mum.tmcheck.services.BlockAttendanceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BlockAttendanceReportServiceImp implements BlockAttendanceReportService {
    public static String REPORT_ID = "block-attendance-report";
    public static String REPORT_TITLE = "Block Attendance Report";

    @Autowired
    BlockAttendanceReportRepository blockAttendanceReportRepository;

//    @Override
//    public List<BlockAttendanceReport> findByBlock(LocalDate block_start, LocalDate block_end) {
//        return blockAttendanceReportRepository.findByBlock(block_start, block_end);
//    }

    @Override
    public List<BlockAttendanceReport> findAllByStudentRegIdOrderByBlockStartDesc(String studentRegId) {
        return blockAttendanceReportRepository.findAllByStudentRegIdOrderByBlockStartDesc(studentRegId);
    }

    @Override
    public BlockAttendanceReport findByStudentRegIdAndBlockId(String studentRegId, long blockId) {
        return blockAttendanceReportRepository.findByStudentRegIdAndBlockId(studentRegId, blockId);
    }
}
