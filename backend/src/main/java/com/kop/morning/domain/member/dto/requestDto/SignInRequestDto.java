package com.kop.morning.domain.member.dto.requestDto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SignInRequestDto {
    private String email;
    private String password;
}
