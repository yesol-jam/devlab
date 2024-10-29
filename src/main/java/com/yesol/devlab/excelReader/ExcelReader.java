package com.yesol.devlab.excelReader;

import com.yesol.devlab.vo.Users;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelReader {
    public List<Users> readExcel() throws IOException {

        //읽어들일 엑셀 경로
        String filePath = "C:/Temp/excelUpload/test.xlsx";
        
        //엑셀의 user arrayList로 리턴
        List<Users> usersList = new ArrayList<>();

        try(
                FileInputStream fis = new FileInputStream(new File(filePath));
                Workbook workbook = new XSSFWorkbook(fis)){

                Sheet sheet = workbook.getSheetAt(0);



                for (Row row : sheet) {

                    //인덱스인 첫째 줄은 무시
                    if(row.getRowNum() == 0){continue;}

                    Users users = new Users();

                    for (Cell cell : row) {

                        // 컬럼별로 users 에 값 세팅
                        switch (cell.getColumnIndex()) {
                            case 0:
                                users.setUser_id(cell.getStringCellValue());
                                break;
                            case 1:
                                users.setPassword(String.valueOf(cell.getNumericCellValue()));
                                break;
                            case 2:
                                users.setName(cell.getStringCellValue());
                                break;
                            case 3:
                                users.setEmail(cell.getStringCellValue());
                                break;
                            default:
                                System.out.print("Error : Unknown Cell Type\t");
                        }
                    }
                    usersList.add(users);
                }

                for ( Users users:usersList ) {
                    //System.out.println(users.toString());
                }

        }catch(Exception e){
            e.printStackTrace();
        }

        return usersList;
    }

}
