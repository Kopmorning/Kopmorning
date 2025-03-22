package com.kop.morning.domain.member.service;

import com.kop.morning.domain.member.dto.requestDto.SignInRequestDto;
import com.kop.morning.domain.member.dto.requestDto.SignUpRequestDto;

public interface MemberService {
    void signIn(SignInRequestDto requestDto); // 로그인 처리 메서드

    void signUp(SignUpRequestDto requestDto);
}
