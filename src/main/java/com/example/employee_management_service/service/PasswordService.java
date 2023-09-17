package com.example.employee_management_service.service;

import com.example.employee_management_service.dao.EmployeeDao;
import com.example.employee_management_service.dto.PasswordDto;
import com.example.employee_management_service.models.Employee;
import com.example.employee_management_service.utils.PasswordHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {

    @Autowired
    EmployeeAuthService employeeAuthService;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    PasswordHashUtil passwordHashUtil;

    public String updatePassword(PasswordDto passwordDto,String accessToken){
        String empId= employeeAuthService.validateAccessToken(accessToken);
        Optional<Employee> employee=employeeDao.findById(empId);
        String password=employee.get().getPassword();
        if(passwordHashUtil.matchPassword(passwordDto.getOldPassword(),password)){
            if(passwordDto.getNewPassword().equals(passwordDto.getConfirmNewPassword())){
                String newPasswrod=passwordHashUtil.encodePassword(passwordDto.getNewPassword());
                employee.get().setPassword(newPasswrod);
                employeeDao.save(employee.get());
                return "Sucessfull";
            }else{
                throw new RuntimeException("Please provide same password");
            }
        }else{
            throw new RuntimeException("Please provide correct old password");
        }

    }


}
