package com.goota.timesheet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetApproverResponse {
    private Long id;
    private String fullName;
    private Long userId;
    private String approverLabel;
    private Integer sequenceOrder;
}
