package com.ssafy.enjoytrip.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@Entity
@Table(name="likes")
public class likes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;// primary  key

    @Column(name="user_id")
    @NotNull
    private Long id;
    @Column(nullable = false)
    private int plan_id; // foreign key

}
