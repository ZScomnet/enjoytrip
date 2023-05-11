package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="plan_id")
    private int plan_id; // primary key
    @Column(name="user_id")
    private Long id; // foreign key
    @Column(name="plan_name")
    private String planName;
    private int like;

}
