package com.kop.morning.domain.member.service;

import com.kop.morning.global.token.dto.JwtToken;

public interface MemberService {
    JwtToken signIn(String username, String password); // 로그인 처리 메서드
}
