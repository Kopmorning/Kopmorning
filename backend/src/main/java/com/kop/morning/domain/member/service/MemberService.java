package com.kop.morning.domain.member.service;

import com.kop.morning.domain.member.dto.requestDto.SignInRequestDto;
import com.kop.morning.domain.member.dto.requestDto.SignUpRequestDto;
import com.kop.morning.global.token.dto.JwtToken;

public interface MemberService {
    JwtToken signIn(SignInRequestDto requestDto); // 로그인 처리 메서드

    void signUp(SignUpRequestDto requestDto);
}
