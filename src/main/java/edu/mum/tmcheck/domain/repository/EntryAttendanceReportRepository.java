package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.reports.EntryAttendanceReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryAttendanceReportRepository {
    @Query(value = "SELECT a FROM AttendanceReport AS a WHERE a.entry = :entry")
    public List<EntryAttendanceReport> findByEntry(String entry);
}
