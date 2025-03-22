package com.kop.morning.domain.member.service;

import com.kop.morning.domain.member.dto.requestDto.SignInRequestDto;
import com.kop.morning.domain.member.dto.requestDto.SignUpRequestDto;
import com.kop.morning.domain.member.entity.Member;
import com.kop.morning.domain.member.entity.Role;
import com.kop.morning.domain.member.repository.MemberRepository;
import com.kop.morning.global.Utils.CookieUtil;
import com.kop.morning.global.token.dto.JwtToken;
import com.kop.morning.global.token.dto.TokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final CookieUtil util;

    @Override
    public void signIn(SignInRequestDto requestDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            JwtToken jwtToken = tokenProvider.generateToken(authentication);

            util.addCookie("accessToken", jwtToken.getAccessToken());
            util.addCookie("refreshToken", jwtToken.getRefreshToken());

        } catch (Exception e) {
            log.error("🚨 authenticationManager.authenticate() 예외 발생!", e);
            throw e;
        }
    }

    @Override
    public void signUp(SignUpRequestDto requestDto) {
        Member member = Member.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .email(requestDto.getEmail())
                .nickname(requestDto.getNickname())
                .provider("form")
                .providerId("form")
                .role(Role.USER)
                .build();

        memberRepository.save(member);
    }
}
