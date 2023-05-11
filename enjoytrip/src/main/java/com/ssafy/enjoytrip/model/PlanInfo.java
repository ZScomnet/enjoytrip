package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name="plan_info")
public class PlanInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id; // primary key
    @Column(name="plan_id")
    private int planId; // foreign key
    @Column(name="content_id")
    private int contentId;
    @Column(name="plan_day")
    private LocalDateTime planDay;

//    @PrePersist
//    public void planDay() {
//        this.planDay = LocalDateTime.now();
//    }

}
