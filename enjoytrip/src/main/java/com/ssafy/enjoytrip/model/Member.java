package com.ssafy.enjoytrip.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member extends User{
    private String username;
    private String address;
    private int age;
    private String phone_number;
}
