package com.example.employee_management_service.models;

import com.example.employee_management_service.models.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee_details")
@Getter
@Setter
@ToString
public class Employee {

    private String _id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String dob;
    private Gender sex;
    private String joiningDate;
    private String password;
}

