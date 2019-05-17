package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.serviceimp.BlockEndEachStudentMeditationData;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


public interface ExcelReportGeneratorService {

    public ByteArrayInputStream ExtraCreditToExcel(List<BlockEndEachStudentMeditationData> ECData) throws IOException;

    public ByteArrayInputStream EntryMeditationAttendanceReportToExcel(List<Attendance> AttendanceData) throws  IOException;
}
