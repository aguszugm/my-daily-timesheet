package com.goota.timesheet.dto;

import com.goota.timesheet.entity.enums.WorkingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetEntryResponse {
    private Long id;
    private Long timesheetId;
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
