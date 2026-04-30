package com.goota.timesheet.service;

import com.goota.timesheet.dto.HolidayRequest;
import com.goota.timesheet.dto.HolidayResponse;
import com.goota.timesheet.entity.Holiday;
import com.goota.timesheet.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    public List<HolidayResponse> getAllHolidays() {
        return holidayRepository.findAll().stream()
                .map(h -> new HolidayResponse(h.getId(), h.getDate(), h.getHolidayName()))
                .collect(Collectors.toList());
    }

    public HolidayResponse getHolidayById(Long id) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holiday not found with id: " + id));
        return new HolidayResponse(holiday.getId(), holiday.getDate(), holiday.getHolidayName());
    }

    public HolidayResponse createHoliday(HolidayRequest request) {
        Holiday holiday = Holiday.builder()
                .date(request.getDate())
                .holidayName(request.getHolidayName())
                .build();
        Holiday saved = holidayRepository.save(holiday);
        return new HolidayResponse(saved.getId(), saved.getDate(), saved.getHolidayName());
    }

    public HolidayResponse updateHoliday(Long id, HolidayRequest request) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holiday not found with id: " + id));
        holiday.setDate(request.getDate());
        holiday.setHolidayName(request.getHolidayName());
        Holiday saved = holidayRepository.save(holiday);
        return new HolidayResponse(saved.getId(), saved.getDate(), saved.getHolidayName());
    }

    public void deleteHoliday(Long id) {
        holidayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Holiday not found with id: " + id));
        holidayRepository.deleteById(id);
    }
}
