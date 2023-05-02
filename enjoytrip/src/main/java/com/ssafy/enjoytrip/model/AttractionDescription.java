package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="attraction_description")
public class AttractionDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="content_id")
    private Long id; // primary key
    @Column(length=100)
    private String homePage;
    @Column(length=10000)
    private String overview;
    @Column(length=45)
    private String telName;
}
