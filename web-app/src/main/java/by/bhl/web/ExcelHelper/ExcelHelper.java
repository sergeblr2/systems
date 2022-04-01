package by.bhl.web.ExcelHelper;

import by.bhl.model.PersonalComputer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {
  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  static String[] HEADERs = { "Id", "PC Name", "PC Level", "PC Status" };
  static String SHEET = "Systems";

  public static ByteArrayInputStream pcsToExcel(List<PersonalComputer> pcs) {

    try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
      Sheet sheet = workbook.createSheet(SHEET);

      Row headerRow = sheet.createRow(0);

      for (int col = 0; col < HEADERs.length; col++) {
        Cell cell = headerRow.createCell(col);
        cell.setCellValue(HEADERs[col]);
      }

      int rowIdx = 1;
      for (PersonalComputer pc : pcs) {
        Row row = sheet.createRow(rowIdx++);

        row.createCell(0).setCellValue(pc.getId());
        row.createCell(1).setCellValue(pc.getName());
        row.createCell(2).setCellValue(pc.getLevel());
        row.createCell(3).setCellValue(pc.getStatus());
      }

      workbook.write(out);
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
    }
  }

}
