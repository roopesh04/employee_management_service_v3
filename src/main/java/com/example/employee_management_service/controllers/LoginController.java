package com.example.employee_management_service.controllers;

import com.example.employee_management_service.dto.LoginServiceDto;
import com.example.employee_management_service.dto.ResponseDto;
import com.example.employee_management_service.service.LoginService;
import com.example.employee_management_service.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public ResponseDto loginTheUser(@RequestBody LoginServiceDto loginServiceDto) {
        return loginService.generateAccessToken(loginServiceDto);
    }

    @PostMapping("/validate-token")
    public String validateToken(@RequestHeader(Constants.Authorization) String accessToken){
        return loginService.validateAccessToken(accessToken);
    }

    @PostMapping("/signout")
    public String signOutUser(@RequestHeader(Constants.Authorization) String accessToken){
        return loginService.signOutUser(accessToken);
    }
}

