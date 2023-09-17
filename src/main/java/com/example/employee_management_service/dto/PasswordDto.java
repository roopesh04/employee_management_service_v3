package com.example.employee_management_service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PasswordDto {

    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
