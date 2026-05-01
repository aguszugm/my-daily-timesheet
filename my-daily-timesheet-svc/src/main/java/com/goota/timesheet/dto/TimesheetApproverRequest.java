package com.goota.timesheet.dto;

import lombok.Data;

@Data
public class TimesheetApproverRequest {
    private String fullName;
    private Long userId;
    private String approverLabel;
    private Integer sequenceOrder;
}
