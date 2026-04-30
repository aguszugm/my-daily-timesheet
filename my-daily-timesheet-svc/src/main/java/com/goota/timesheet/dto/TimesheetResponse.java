package com.goota.timesheet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetResponse {
    private Long id;
    private String periodName;
    private LocalDate periodStartDate;
    private LocalDate periodEndDate;
    private Integer numberOfWorkingDay;
}
