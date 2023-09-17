package com.example.employee_management_service.controllers;

import com.example.employee_management_service.dto.PasswordDto;
import com.example.employee_management_service.service.PasswordService;
import com.example.employee_management_service.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password")
@CrossOrigin(origins = "*")
public class PasswordController {

    @Autowired
    PasswordService passwordService;

    @PutMapping
    public String updatePassword(@RequestBody PasswordDto passwordDto,
                                 @RequestHeader(Constants.Authorization) String accessToken){
        return passwordService.updatePassword(passwordDto, accessToken);
    }
}
