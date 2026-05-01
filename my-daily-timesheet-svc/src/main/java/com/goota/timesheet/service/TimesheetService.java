package com.goota.timesheet.service;

import com.goota.timesheet.dto.TimesheetApproverRequest;
import com.goota.timesheet.dto.TimesheetApproverResponse;
import com.goota.timesheet.dto.TimesheetRequest;
import com.goota.timesheet.dto.TimesheetResponse;
import com.goota.timesheet.entity.Timesheet;
import com.goota.timesheet.entity.TimesheetApprover;
import com.goota.timesheet.entity.User;
import com.goota.timesheet.repository.TimesheetApproverRepository;
import com.goota.timesheet.repository.TimesheetRepository;
import com.goota.timesheet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimesheetService {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private TimesheetApproverRepository timesheetApproverRepository;

    @Autowired
    private UserRepository userRepository;

    private TimesheetResponse toResponse(Timesheet t) {
        return new TimesheetResponse(t.getId(), t.getPeriodName(), t.getPeriodStartDate(), t.getPeriodEndDate(), t.getNumberOfWorkingDay());
    }

    public List<TimesheetResponse> getAllTimesheets() {
        return timesheetRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public TimesheetResponse getTimesheetById(Long id) {
        Timesheet ts = timesheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet not found with id: " + id));
        return toResponse(ts);
    }

    public TimesheetResponse createTimesheet(TimesheetRequest request) {
        Timesheet ts = Timesheet.builder()
                .periodName(request.getPeriodName())
                .periodStartDate(request.getPeriodStartDate())
                .periodEndDate(request.getPeriodEndDate())
                .numberOfWorkingDay(request.getNumberOfWorkingDay())
                .build();
        return toResponse(timesheetRepository.save(ts));
    }

    public TimesheetResponse updateTimesheet(Long id, TimesheetRequest request) {
        Timesheet ts = timesheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet not found with id: " + id));
        ts.setPeriodName(request.getPeriodName());
        ts.setPeriodStartDate(request.getPeriodStartDate());
        ts.setPeriodEndDate(request.getPeriodEndDate());
        ts.setNumberOfWorkingDay(request.getNumberOfWorkingDay());
        return toResponse(timesheetRepository.save(ts));
    }

    public void deleteTimesheet(Long id) {
        timesheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet not found with id: " + id));
        timesheetRepository.deleteById(id);
    }

    public TimesheetApproverResponse addApprover(TimesheetApproverRequest request) {
        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId()).orElse(null);
        }
        TimesheetApprover approver = TimesheetApprover.builder()
                .fullName(request.getFullName())
                .user(user)
                .approverLabel(request.getApproverLabel())
                .sequenceOrder(request.getSequenceOrder())
                .build();
        TimesheetApprover saved = timesheetApproverRepository.save(approver);
        Long userId = saved.getUser() != null ? saved.getUser().getId() : null;
        return new TimesheetApproverResponse(saved.getId(), saved.getFullName(), userId, saved.getApproverLabel(), saved.getSequenceOrder());
    }

    public void removeApprover(Long approverId) {
        timesheetApproverRepository.findById(approverId)
                .orElseThrow(() -> new RuntimeException("Approver not found with id: " + approverId));
        timesheetApproverRepository.deleteById(approverId);
    }
}
