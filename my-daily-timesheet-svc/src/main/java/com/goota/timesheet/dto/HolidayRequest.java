package com.goota.timesheet.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HolidayRequest {
    private LocalDate date;
    private String holidayName;
}
