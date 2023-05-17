package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SignInDto {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
