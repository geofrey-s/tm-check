package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Entry;
import edu.mum.tmcheck.domain.reports.EntryAttendanceReport;
import edu.mum.tmcheck.domain.repository.EntryAttendanceReportRepository;
import edu.mum.tmcheck.services.EntryAttendanceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryAttendanceReportServiceImp implements EntryAttendanceReportService {
    public static String REPORT_ID = "entry-attendance-report";
    public static String REPORT_TITLE = "Entry Attendance Report";

    @Autowired
    EntryAttendanceReportRepository entryAttendanceReportRepository;

    @Autowired
    EntryServiceImp entryServiceImp;

    public List<EntryAttendanceReport> generateByEntry(String entry) {
        return entryAttendanceReportRepository.findByEntry(entry);
    }

    public List<EntryAttendanceReport> generateByEntryId(long id) {
        Entry entry = entryServiceImp.findById(id);
        return entryAttendanceReportRepository.findByEntry(entry.getName());
    }

    public String downloadLink(long entryId) {
        return String.format("/download/%s/%s.xslx", REPORT_ID, entryId);
    }
}
