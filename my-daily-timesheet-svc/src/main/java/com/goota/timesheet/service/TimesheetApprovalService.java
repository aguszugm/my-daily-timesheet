package com.goota.timesheet.service;

import com.goota.timesheet.dto.ApprovalHistoryResponse;
import com.goota.timesheet.entity.Timesheet;
import com.goota.timesheet.entity.TimesheetApprovalHistory;
import com.goota.timesheet.entity.enums.ApprovalStatus;
import com.goota.timesheet.repository.TimesheetApprovalHistoryRepository;
import com.goota.timesheet.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TimesheetApprovalService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private TimesheetApprovalHistoryRepository approvalHistoryRepository;

    public ApprovalHistoryResponse approve(Long timesheetId, Long approverId, String note) {
        return recordApproval(timesheetId, ApprovalStatus.APPROVED, note);
    }

    public ApprovalHistoryResponse reject(Long timesheetId, Long approverId, String note) {
        return recordApproval(timesheetId, ApprovalStatus.REJECTED, note);
    }

    private ApprovalHistoryResponse recordApproval(Long timesheetId, ApprovalStatus status, String note) {
        Timesheet timesheet = timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new RuntimeException("Timesheet not found with id: " + timesheetId));

        TimesheetApprovalHistory history = TimesheetApprovalHistory.builder()
                .timesheet(timesheet)
                .date(LocalDate.now())
                .status(status)
                .note(note)
                .build();
        TimesheetApprovalHistory saved = approvalHistoryRepository.save(history);
        return new ApprovalHistoryResponse(saved.getId(), timesheetId, saved.getDate(), saved.getStatus(), saved.getNote());
    }
}
