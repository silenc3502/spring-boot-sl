package com.example.springspeciallecture.account.controller;

import com.example.springspeciallecture.account.controller.request_form.RegisterNormalAccountRequestForm;
import com.example.springspeciallecture.account.entity.Account;
import com.example.springspeciallecture.account.service.AccountService;
import com.example.springspeciallecture.account_profile.service.AccountProfileService;
import com.example.springspeciallecture.redis_cache.service.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final RedisCacheService redisCacheService;
    private final AccountProfileService accountProfileService;

    @PostMapping("/register")
    public String register(@RequestHeader("Authorization") String authorizationHeader,
                           @RequestBody RegisterNormalAccountRequestForm requestForm) {
        log.info("회원 가입 요청: requestForm={}", requestForm);

        String temporaryUserToken = authorizationHeader.replace("Bearer ", "").trim();

        String accessToken = redisCacheService.getValueByKey(temporaryUserToken, String.class);
        if (accessToken == null) {
            throw new IllegalArgumentException("만료되었거나 잘못된 임시 토큰입니다.");
        }

        Account account = accountService.createAccount(requestForm.toRegisterNormalAccountRequest());
        accountProfileService.createAccountProfile(account, requestForm.toRegisterAccountProfileRequest());

        String userToken = UUID.randomUUID().toString();
        redisCacheService.setKeyAndValue(account.getId(), accessToken);
        redisCacheService.setKeyAndValue(userToken, account.getId());

        redisCacheService.deleteByKey(temporaryUserToken);

        return userToken;
    }
}
