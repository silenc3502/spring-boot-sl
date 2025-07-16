package com.example.springspeciallecture.kakao_authentication.service;

import com.example.springspeciallecture.kakao_authentication.service.response.KakaoLoginResponse;

public interface KakaoAuthenticationService {
    KakaoLoginResponse handleLogin(String code);
}