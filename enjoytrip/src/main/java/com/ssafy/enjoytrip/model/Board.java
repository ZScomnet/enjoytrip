package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Board{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;//Member username
    private String text;
    private int group_id;

    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    @Column(name = "created")
    private LocalDateTime created;

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean blocked;
}
