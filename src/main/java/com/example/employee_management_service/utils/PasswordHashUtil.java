package com.example.employee_management_service.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHashUtil {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public Boolean matchPassword(String password, String encodedString){
        return bCryptPasswordEncoder.matches(password,encodedString);
    }
}
