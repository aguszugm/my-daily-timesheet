package com.goota.timesheet.entity;

import com.goota.timesheet.entity.enums.WorkingType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "timesheet_entry")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimesheetEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "timesheet_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Timesheet timesheet;

    @Column
    private LocalDate date;

    @Column(name = "vendor_name")
    private String vendorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @Column
    private String spk;

    @Column(name = "hour_start")
    private LocalTime hourStart;

    @Column(name = "hour_end")
    private LocalTime hourEnd;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Project project;

    @Column
    private String activity;

    @Enumerated(EnumType.STRING)
    @Column(name = "working_type")
    private WorkingType workingType;

    @CreatedDate
    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime;

    @LastModifiedDate
    @Column(name = "updated_datetime")
    private LocalDateTime updatedDatetime;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;
}
