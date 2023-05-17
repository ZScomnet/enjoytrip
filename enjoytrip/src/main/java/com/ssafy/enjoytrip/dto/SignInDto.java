package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class SignInDto {
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String username;
    @NonNull
    private String address;
    @NonNull
    private int age;
    @NonNull
    private String phone_number;
}
