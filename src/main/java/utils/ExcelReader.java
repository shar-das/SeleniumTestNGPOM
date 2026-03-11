package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    static FileInputStream file;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public static Object[][] readExcel(String sheetName) throws IOException {

        file = new FileInputStream("src/main/resources/loginTestData.xlsx");
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        for(int i=0; i<rows; i++)  {
            for(int j=0; j<cols; j++) {
                data[i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
            }
        }

        return data;
    }

}
