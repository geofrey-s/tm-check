package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Attendance;
import edu.mum.tmcheck.serviceimp.BlockEndEachStudentMeditationData;
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
    public ByteArrayInputStream ExtraCreditToExcel(List<BlockEndEachStudentMeditationData> ECData) throws IOException {
        String[] COLUMNs = {"Student Id", "Student Name", "Days Attended", "Total Days In Block", "Percentage Attended", "Total Extra Credits"};
        try(
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ){
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Customers");

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
            for (BlockEndEachStudentMeditationData studentdata : ECData)
            {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(studentdata.getStudent().getStudentRegId());
                row.createCell(1).setCellValue(studentdata.getStudent().getName());
                row.createCell(2).setCellValue(studentdata.getNoofdaysattended());
                row.createCell(3).setCellValue(studentdata.getTotalnoofdays());
                row.createCell(4).setCellValue(studentdata.getPercentageattended());
                row.createCell(5).setCellValue(studentdata.getTotalmarks());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    @Override
    public ByteArrayInputStream EntryMeditationAttendanceReportToExcel(List<Attendance> AttendanceData) throws IOException {
        return null;
    }

}