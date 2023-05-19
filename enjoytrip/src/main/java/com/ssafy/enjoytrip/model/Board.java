package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean blocked;
}
