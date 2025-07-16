package com.example.springspeciallecture.account.service;

import com.example.springspeciallecture.account.entity.Account;
import com.example.springspeciallecture.account.service.request.RegisterNormalAccountRequest;

public interface AccountService {
    Account createAccount(RegisterNormalAccountRequest request);
}
