package com.kop.morning.domain.member.dto.requestDto;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String password;
    private String email;
}
