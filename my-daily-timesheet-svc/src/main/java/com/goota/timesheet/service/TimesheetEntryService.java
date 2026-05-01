package com.goota.timesheet.service;

import com.goota.timesheet.dto.TimesheetEntryRequest;
import com.goota.timesheet.dto.TimesheetEntryResponse;
import com.goota.timesheet.entity.Project;
import com.goota.timesheet.entity.Timesheet;
import com.goota.timesheet.entity.TimesheetEntry;
import com.goota.timesheet.entity.User;
import com.goota.timesheet.repository.ProjectRepository;
import com.goota.timesheet.repository.TimesheetEntryRepository;
import com.goota.timesheet.repository.TimesheetRepository;
import com.goota.timesheet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimesheetEntryService {

    @Autowired
    private TimesheetEntryRepository timesheetEntryRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private TimesheetEntryResponse toResponse(TimesheetEntry e) {
        Long userId = e.getUser() != null ? e.getUser().getId() : null;
        Long projectId = e.getProject() != null ? e.getProject().getId() : null;
        return new TimesheetEntryResponse(e.getId(), e.getTimesheet().getId(), e.getDate(),
                e.getVendorName(), userId, e.getSpk(), e.getHourStart(), e.getHourEnd(),
                e.getDescription(), projectId, e.getActivity(), e.getWorkingType());
    }

    public List<TimesheetEntryResponse> getEntriesByTimesheetId(Long timesheetId) {
        return timesheetEntryRepository.findByTimesheetId(timesheetId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public TimesheetEntryResponse getEntryById(Long timesheetId, Long entryId) {
        TimesheetEntry entry = timesheetEntryRepository.findById(entryId)
                .orElseThrow(() -> new RuntimeException("Entry not found with id: " + entryId));
        return toResponse(entry);
    }

    public TimesheetEntryResponse createEntry(Long timesheetId, TimesheetEntryRequest request) {
        Timesheet timesheet = timesheetRepository.findById(timesheetId)
                .orElseThrow(() -> new RuntimeException("Timesheet not found with id: " + timesheetId));
        User user = request.getUserId() != null ? userRepository.findById(request.getUserId()).orElse(null) : null;
        Project project = request.getProjectId() != null ? projectRepository.findById(request.getProjectId()).orElse(null) : null;

        TimesheetEntry entry = TimesheetEntry.builder()
                .timesheet(timesheet)
                .date(request.getDate())
                .vendorName(request.getVendorName())
                .user(user)
                .spk(request.getSpk())
                .hourStart(request.getHourStart())
                .hourEnd(request.getHourEnd())
                .description(request.getDescription())
                .project(project)
                .activity(request.getActivity())
                .workingType(request.getWorkingType())
                .build();
        return toResponse(timesheetEntryRepository.save(entry));
    }

    public TimesheetEntryResponse updateEntry(Long timesheetId, Long entryId, TimesheetEntryRequest request) {
        TimesheetEntry entry = timesheetEntryRepository.findById(entryId)
                .orElseThrow(() -> new RuntimeException("Entry not found with id: " + entryId));
        User user = request.getUserId() != null ? userRepository.findById(request.getUserId()).orElse(null) : null;
        Project project = request.getProjectId() != null ? projectRepository.findById(request.getProjectId()).orElse(null) : null;

        entry.setDate(request.getDate());
        entry.setVendorName(request.getVendorName());
        entry.setUser(user);
        entry.setSpk(request.getSpk());
        entry.setHourStart(request.getHourStart());
        entry.setHourEnd(request.getHourEnd());
        entry.setDescription(request.getDescription());
        entry.setProject(project);
        entry.setActivity(request.getActivity());
        entry.setWorkingType(request.getWorkingType());
        return toResponse(timesheetEntryRepository.save(entry));
    }

    public void deleteEntry(Long timesheetId, Long entryId) {
        timesheetEntryRepository.findById(entryId)
                .orElseThrow(() -> new RuntimeException("Entry not found with id: " + entryId));
        timesheetEntryRepository.deleteById(entryId);
    }
}
