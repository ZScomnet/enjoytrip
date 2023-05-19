package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name="board_group")
public class BoardGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    private int group_id;

    private String group_name;

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean write_permission;

    @ColumnDefault("false")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean read_permission;

}
