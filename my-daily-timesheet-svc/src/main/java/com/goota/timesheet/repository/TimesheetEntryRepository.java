package com.goota.timesheet.repository;

import com.goota.timesheet.entity.TimesheetEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TimesheetEntryRepository extends JpaRepository<TimesheetEntry, Long> {
    List<TimesheetEntry> findByTimesheetId(Long timesheetId);
}
