package com.example.employee_management_service.service;

import com.example.employee_management_service.dao.EmployeeDao;
import com.example.employee_management_service.dto.LoginServiceDto;
import com.example.employee_management_service.dto.ResponseDto;
import com.example.employee_management_service.models.Employee;
import com.example.employee_management_service.utils.PasswordHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    PasswordHashUtil passwordHashUtil;

    @Autowired
    EmployeeAuthService employeeAuthService;

    public ResponseDto generateAccessToken(LoginServiceDto loginServiceDto) {
        Optional<Employee> employee = employeeDao.findById(loginServiceDto.getEmpId());
        if (employee.isEmpty()) throw new RuntimeException("Employee Doesn't exit");

        if (passwordHashUtil.matchPassword(loginServiceDto.getPassword(), employee.get().getPassword())) {
            ResponseDto responseDto=new ResponseDto();
            responseDto.setAccessToken(employeeAuthService.generateAccessTokenForUser(loginServiceDto.getEmpId()));
            return responseDto;
       }
        else throw new RuntimeException("Wrong Password");
    }

    public String validateAccessToken(String accessToken){
        return employeeAuthService.validateAccessToken(accessToken);
    }

    public String signOutUser(String accessToken){
        employeeAuthService.validateAccessToken(accessToken);
        employeeAuthService.deleteAccessToken(accessToken);
        return "Sucessfull";
    }

}

