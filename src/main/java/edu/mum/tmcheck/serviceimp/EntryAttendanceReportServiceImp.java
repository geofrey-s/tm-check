package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.reports.EntryAttendanceReport;
import edu.mum.tmcheck.domain.repository.EntryAttendanceReportRepository;
import edu.mum.tmcheck.services.EntryAttendanceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryAttendanceReportServiceImp implements EntryAttendanceReportService {
    @Autowired
    EntryAttendanceReportRepository entryAttendanceReportRepository;

    public List<EntryAttendanceReport> generateByEntry(String entry) {
        return entryAttendanceReportRepository.findByEntry(entry);
    }
}
