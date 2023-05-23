package com.ssafy.enjoytrip.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends User{
    @Column(length=30, unique = true)
    private String username;
    private String profileimg;
    private String phone_number;
}
