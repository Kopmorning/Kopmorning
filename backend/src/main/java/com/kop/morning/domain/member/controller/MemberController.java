package com.kop.morning.domain.member.controller;

import com.kop.morning.domain.member.dto.requestDto.SignInRequestDto;
import com.kop.morning.domain.member.dto.requestDto.SignUpRequestDto;
import com.kop.morning.domain.member.service.MemberService;
import com.kop.morning.global.token.dto.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<JwtToken> signIn(@RequestBody SignInRequestDto requestDto) {
        return ResponseEntity.ok(memberService.signIn(requestDto));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDto requestDto) {
        memberService.signUp(requestDto);
        return ResponseEntity.ok("Sign up successful");
    }
}
