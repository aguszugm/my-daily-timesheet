package com.goota.timesheet.repository;

import com.goota.timesheet.entity.TimesheetApprovalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimesheetApprovalHistoryRepository extends JpaRepository<TimesheetApprovalHistory, Long> {
    List<TimesheetApprovalHistory> findByTimesheetId(Long timesheetId);
}
