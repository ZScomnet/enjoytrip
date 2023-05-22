package com.ssafy.enjoytrip.dto;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
    private Long user_id;
    private String email;
    private String username;
    private String password;
//
//    private String email;
//    private String password;
//    private String address;
//    private int age;
//    private String phone_number;
}
