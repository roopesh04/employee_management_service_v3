package com.example.employee_management_service.models;

import com.example.employee_management_service.models.enums.LeaveType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Getter
@Setter
@Document(collection = "leave_requests")
public class LeaveRequest {
    private String _id;
    private String empId;
    private String to;
    private String from;
    private Boolean status;
    private String description;
    private String managerId;
    private LeaveType leaveType;

}
