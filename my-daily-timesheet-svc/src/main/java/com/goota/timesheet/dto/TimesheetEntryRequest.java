package com.goota.timesheet.dto;

import com.goota.timesheet.entity.enums.WorkingType;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TimesheetEntryRequest {
    private LocalDate date;
    private String vendorName;
    private Long userId;
    private String spk;
    private LocalTime hourStart;
    private LocalTime hourEnd;
    private String description;
    private Long projectId;
    private String activity;
    private WorkingType workingType;
}
