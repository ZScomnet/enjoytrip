package com.ssafy.enjoytrip.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Getter
@Setter
public class Member extends User{
    @UniqueElements
    private String username;
    private String address;
    private int age;
    private String phone_number;
}
