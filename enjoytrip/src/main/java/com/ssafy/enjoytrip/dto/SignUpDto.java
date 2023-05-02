package com.ssafy.enjoytrip.dto;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String email;
    private String password;
    private String username;
    private String address;
    private int age;
    private String phone_number;
}
