package com.yesol.devlab.excelReader;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ExcelReader {
    public List<String> readExcel() throws IOException {
        FileInputStream file = new FileInputStream("test.xlsx");
        //test2
        //test
        return null;
    }

}
