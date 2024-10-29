package com.yesol.devlab.controller;
import com.yesol.devlab.repository.UsersRepository;
import com.yesol.devlab.vo.Users;
import lombok.RequiredArgsConstructor;
import com.yesol.devlab.excelReader.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExcelReadController {

    private final ExcelReader excelReader;
    @Autowired
    private UsersRepository usersRepository;
    // 생성자를 통한 의존성 주입

    @GetMapping("/api/excelRead")
    public String readExcelFile()  {
        System.out.println("server test");
        try {
            List<Users> usersList = excelReader.readExcel();
            usersRepository.saveAll(usersList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
