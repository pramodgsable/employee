package com.pramod.swissre.employee.fileUtility;

import com.pramod.swissre.employee.model.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelReader {
    public static List<Employee> readExcel(String path) throws Exception {
        List<Employee> employees = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File(path));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // skip header
            Employee e = new Employee();
            e.setId((long) row.getCell(0).getNumericCellValue());
            e.setName(row.getCell(1).getStringCellValue());
            e.setCity(row.getCell(2).getStringCellValue());
            e.setState(row.getCell(3).getStringCellValue());
            e.setCategory(row.getCell(4).getStringCellValue());
            e.setManagerId(row.getCell(5) == null ? null : (long) row.getCell(5).getNumericCellValue());
            e.setSalary(row.getCell(6).getNumericCellValue());
            Cell dateCell = row.getCell(7);
            if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
                Date date = dateCell.getDateCellValue();
                e.setDateOfJoining(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            } else {
                // Fallback: try to parse string
                e.setDateOfJoining(LocalDate.parse(dateCell.getStringCellValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            employees.add(e);
        }
        workbook.close();
        return employees;
    }
}