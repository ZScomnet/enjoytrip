package com.ssafy.enjoytrip.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Getter
@Setter
public class Member extends User{
    @Column(length=30, unique = true)
    private String username;
    private String profileimg;
    private String phone_number;
}
