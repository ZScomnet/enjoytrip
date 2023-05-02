package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="attraction_detail")
public class AttractionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="content_id")
    private Long id; // primary key
    private String cat1;
    private String cat2;
    private String cat3;
    @Column(name="created_time", length=14)
    private String createdTime;
    @Column(name="modified_time", length=14)
    private String modifiedTime;
    @Column(length=5)
    private String bookTour;
}
