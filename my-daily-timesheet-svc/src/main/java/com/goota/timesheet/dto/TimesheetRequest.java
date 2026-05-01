package com.goota.timesheet.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TimesheetRequest {
    private String periodName;
    private LocalDate periodStartDate;
    private LocalDate periodEndDate;
    private Integer numberOfWorkingDay;
}
