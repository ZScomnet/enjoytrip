package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Gugun{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sido_code")
    private int sidoCode;
    @Column(name="gugun_name")
    private String gugunName;
    @Column(name="gugun_code")
    private int gugunCode;
}
