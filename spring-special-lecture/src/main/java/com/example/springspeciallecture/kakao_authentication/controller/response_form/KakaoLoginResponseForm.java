package com.example.springspeciallecture.kakao_authentication.controller.response_form;

import com.example.springspeciallecture.account.entity.LoginType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KakaoLoginResponseForm {
    private final boolean newUser;
    private final String token;
    private final String nickname;
    private final String email;
    private final LoginType loginType;

    public static KakaoLoginResponseForm from(boolean newUser, String token, String nickname, String email, LoginType loginType) {
        return new KakaoLoginResponseForm(newUser, token, nickname, email, loginType);
    }
}
