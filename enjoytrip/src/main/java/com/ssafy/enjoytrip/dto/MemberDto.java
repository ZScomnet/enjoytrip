package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class MemberDto {
    @NonNull
    private String username;
    @NonNull
    private String address;
    @NonNull
    private int age;
    @NonNull
    private String phone_number;
}
