package com.goota.timesheet.dto;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String userEmail;
    private String password;
    private String fullName;
    private boolean admin;
}
