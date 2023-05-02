package com.ssafy.enjoytrip.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends User{
    private String username;
    private String address;
    private int age;
    private String phone_number;
}
