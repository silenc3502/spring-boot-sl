package com.example.springspeciallecture.account.service.request;

import com.example.springspeciallecture.account.entity.LoginType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterNormalAccountRequest {
    private final LoginType loginType;
}
