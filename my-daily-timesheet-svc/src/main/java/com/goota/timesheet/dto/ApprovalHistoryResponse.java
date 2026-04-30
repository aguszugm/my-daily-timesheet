package com.goota.timesheet.dto;

import com.goota.timesheet.entity.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalHistoryResponse {
    private Long id;
    private Long timesheetId;
    private LocalDate date;
    private ApprovalStatus status;
    private String note;
}
