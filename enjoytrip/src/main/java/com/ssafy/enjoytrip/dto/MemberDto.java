package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
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
