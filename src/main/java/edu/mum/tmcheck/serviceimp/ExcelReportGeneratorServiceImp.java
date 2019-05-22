package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.domain.reports.ECAttendanceReport;
import edu.mum.tmcheck.domain.reports.EntryAttendanceReport;
import edu.mum.tmcheck.services.ExcelReportGeneratorService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ExcelReportGeneratorServiceImp implements ExcelReportGeneratorService {

    @Override
    public ByteArrayInputStream ExtraCreditToExcel(List<ECAttendanceReport> ECData) throws IOException {
        String[] COLUMNs = {"Student Id", "Student Name", "Days Attended", "Total Days In Block", "Percentage Attended", "Total Extra Credits"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Extra Credit Sheet");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (ECAttendanceReport studentdata : ECData) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(studentdata.getStudentRegId());
                row.createCell(1).setCellValue(studentdata.getName());
                row.createCell(2).setCellValue(studentdata.getStandardTm());
                row.createCell(3).setCellValue(studentdata.getDaysAttended());
                row.createCell(4).setCellValue(studentdata.getOverrallAttendance());
                row.createCell(5).setCellValue(studentdata.getExtraCredit());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    @Override
    public ByteArrayInputStream EntryMeditationAttendanceReportToExcel(List<EntryAttendanceReport> AttendanceData) throws IOException
    {
        String[] COLUMNs = {"Student Id", "Student Name", "Entry", "Start Date", "End Date", "Standard TM", "Retreats", "TM Checks", "Overall %"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Entry Meditation Attendance Report");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (EntryAttendanceReport studentdata : AttendanceData) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(studentdata.getStudentId());
                row.createCell(1).setCellValue(studentdata.getName());
                row.createCell(2).setCellValue(studentdata.getEntry());
                row.createCell(3).setCellValue(studentdata.getEntryStart().toString());
                row.createCell(4).setCellValue(studentdata.getEntryEnd().toString());
                row.createCell(5).setCellValue(studentdata.getStandardTm());
                row.createCell(6).setCellValue(studentdata.getRetreats());
                row.createCell(7).setCellValue(studentdata.getChecks());
                row.createCell(8).setCellValue(studentdata.getOverrallAttendance());;
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

}