package com.example.employee_management_service.models;

import com.example.employee_management_service.models.enums.CareerLevel;
import com.example.employee_management_service.models.enums.EmployeeRole;
import com.example.employee_management_service.models.enums.EmploymentType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "employment_details")
@Getter
@Setter
public class Employment {

    private String empId;
    private String workLocation;
    private EmploymentType employmentType;
    private CareerLevel careerLevel;
    private String managerId;
    private String HRID;
    private String department;
    private List<String> team;
    private EmployeeRole employeeRole;
}


