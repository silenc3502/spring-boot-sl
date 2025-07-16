package com.example.springspeciallecture.account.controller.request_form;

import com.example.springspeciallecture.account.entity.LoginType;
import com.example.springspeciallecture.account.service.request.RegisterNormalAccountRequest;
import com.example.springspeciallecture.account_profile.service.request.RegisterAccountProfileRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterNormalAccountRequestForm {
    final private String email;
    final private String nickname;
    final private LoginType loginType;

    public RegisterNormalAccountRequest toRegisterNormalAccountRequest() {
        return new RegisterNormalAccountRequest(loginType);
    }

    public RegisterAccountProfileRequest toRegisterAccountProfileRequest() {
        return new RegisterAccountProfileRequest(email, nickname);
    }
}
