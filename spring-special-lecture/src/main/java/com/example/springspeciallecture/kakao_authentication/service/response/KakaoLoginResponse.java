package com.example.springspeciallecture.kakao_authentication.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KakaoLoginResponse {
    private final boolean newUser;
    private final String token;
    private final String nickname;
    private final String email;
    private final Long accountId;

    public static KakaoLoginResponse from(boolean newUser, String token, String nickname, String email, Long accountId) {
        return new KakaoLoginResponse(newUser, token, nickname, email, accountId);
    }
}
