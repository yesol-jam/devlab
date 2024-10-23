package com.yesol.devlab.excelReader;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
@Component
public class ExcelReader {
    public String readExcel() throws IOException {
        String filePath = "C:/Temp/excelUpload/test.xlsx";

        try(
                FileInputStream fis = new FileInputStream(new File(filePath));
                Workbook workbook = new XSSFWorkbook(fis)){

                Sheet sheet = workbook.getSheetAt(0);

                for (Row row : sheet) {
                    for (Cell cell : row) {
                        // 셀의 타입에 따라 값을 다르게 가져오기
                        switch (cell.getCellType()) {
                            case STRING:
                                System.out.print(cell.getStringCellValue() + "\t");
                                break;
                            case NUMERIC:
                                System.out.print(cell.getNumericCellValue() + "\t");
                                break;
                            case BOOLEAN:
                                System.out.print(cell.getBooleanCellValue() + "\t");
                                break;
                            case FORMULA:
                                System.out.print(cell.getCellFormula() + "\t");
                                break;
                            default:
                                System.out.print("Unknown Cell Type\t");
                        }


                    }
                    System.out.println(); // 한 행이 끝날 때 줄 바꿈
                }



        }catch(Exception e){}

        return null;
    }

}
