package com.goota.timesheet.dto;

import lombok.Data;

@Data
public class ApprovalRequest {
    private Long approverId;
    private String note;
}
