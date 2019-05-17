package edu.mum.tmcheck.controllers;

import edu.mum.tmcheck.domain.entities.User;
import edu.mum.tmcheck.serviceimp.BlockEndEachStudentMeditationData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public static ByteArrayInputStream customersToExcel(List<User> ECData) throws IOException {
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
            for (User studentdata : ECData)
            {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(studentdata.getId());
                row.createCell(1).setCellValue(studentdata.getName());
                row.createCell(2).setCellValue(studentdata.getUsername());
                row.createCell(3).setCellValue(studentdata.getPassword());
                row.createCell(4).setCellValue(studentdata.getClass().getSimpleName());
                row.createCell(5).setCellValue(studentdata.getId());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}