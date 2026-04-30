package com.goota.timesheet.repository;

import com.goota.timesheet.entity.TimesheetApprover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetApproverRepository extends JpaRepository<TimesheetApprover, Long> {
}
