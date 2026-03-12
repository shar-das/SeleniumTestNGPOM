package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    static FileInputStream fis;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static List<String> expectedProducts;
    static int rows, cols, i, j;

    public static Object[][] readloginCredentialsExcel(String sheetName) throws IOException {

        fis = new FileInputStream("src/main/resources/loginTestData.xlsx");
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        rows = sheet.getLastRowNum();
        cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][cols];

        for(i=0; i<rows; i++)  {
            for(j=0; j<cols; j++) {
                data[i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
            }
        }

        return data;
    }

    public static List<String> readProductsListExcel(String fileName) throws IOException {
        fis = new FileInputStream("src/main/resources/" + fileName + ".xlsx");
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(0);

        rows = sheet.getLastRowNum();
        expectedProducts = new ArrayList<>();

        for(i=0; i<=rows; i++) {
            expectedProducts.add(sheet.getRow(i).getCell(0).getStringCellValue());
        }

        return expectedProducts;
    }

}
