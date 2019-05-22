package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.reports.ECAttendanceReport;
import edu.mum.tmcheck.domain.repository.ECAttendanceReportRepository;
import edu.mum.tmcheck.services.ECAttendanceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ECAttendanceReportServiceImp implements ECAttendanceReportService {
    public static String REPORT_ID = "ec-attendance-report";
    public static String REPORT_TITLE = "Extra Credit Attendance Report";

    @Autowired
    ECAttendanceReportRepository ecAttendanceReportRepository;

    @Override
    public List<ECAttendanceReport> findAllByBlockId(long id) {
        return ecAttendanceReportRepository.findAllByBlockId(id);
    }

    @Override
    public List<ECAttendanceReport> findAllByFacultyIdAndBlockId(long facultyId, long blockId) {
        return ecAttendanceReportRepository.findAllByFacultyIdAndBlockId(facultyId, blockId);
    }
}
