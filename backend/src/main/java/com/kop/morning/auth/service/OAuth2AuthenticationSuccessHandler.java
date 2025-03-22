package com.kop.morning.auth.service;

import com.kop.morning.global.Utils.CookieUtil;
import com.kop.morning.global.token.dto.JwtToken;
import com.kop.morning.global.token.dto.TokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final CookieUtil util;
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JwtToken token = tokenProvider.generateToken(authentication);

        util.addCookie("accessToken", token.getAccessToken());
        util.addCookie("refreshToken", token.getRefreshToken());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.sendRedirect("/"); // 로그인 성공 후 리디렉션 URL (필요시 수정)
    }
}