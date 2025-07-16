package com.example.springspeciallecture.kakao_authentication.controller;

import com.example.springspeciallecture.account.entity.LoginType;
import com.example.springspeciallecture.account.service.AccountService;
import com.example.springspeciallecture.account_profile.service.AccountProfileService;
import com.example.springspeciallecture.kakao_authentication.controller.response_form.KakaoLoginResponseForm;
import com.example.springspeciallecture.kakao_authentication.service.KakaoAuthenticationService;
import com.example.springspeciallecture.kakao_authentication.service.response.KakaoLoginResponse;
import com.example.springspeciallecture.redis_cache.service.RedisCacheService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao-authentication")
public class KakaoAuthenticationController {
    final private KakaoAuthenticationService kakaoAuthenticationService;
    final private RedisCacheService redisCacheService;

    @GetMapping("/login")
    @Transactional
    public KakaoLoginResponseForm requestAccessToken(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        log.info("requestAccessToken(): code {}", code);
        try {
            KakaoLoginResponse loginResponse = kakaoAuthenticationService.handleLogin(code);
            String redisToken = loginResponse.isNewUser()
                    ? createTemporaryUserToken(loginResponse.getToken())
                    : createUserTokenWithAccessToken(loginResponse.getAccountId(), loginResponse.getToken());

            return KakaoLoginResponseForm.from(
                    loginResponse.isNewUser(),
                    redisToken,
                    loginResponse.getNickname(),
                    loginResponse.getEmail(),
                    LoginType.KAKAO
            );
        } catch (Exception e) {
            log.error("Kakao 로그인 에러", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "카카오 로그인 실패: " + e.getMessage());
            return null;
        }
    }

    private String createUserTokenWithAccessToken(Long accountId, String accessToken) {
        try {
            String userToken = UUID.randomUUID().toString();
            redisCacheService.setKeyAndValue(accountId, accessToken);
            redisCacheService.setKeyAndValue(userToken, accountId);
            return userToken;
        } catch (Exception e) {
            throw new RuntimeException("Error storing token in Redis: " + e.getMessage());
        }
    }

    private String createTemporaryUserToken(String accessToken) {
        try {
            String userToken = UUID.randomUUID().toString();
            redisCacheService.setKeyAndValue(userToken, accessToken, Duration.ofMinutes(5));
            return userToken;
        } catch (Exception e) {
            throw new RuntimeException("Error storing token in Redis: " + e.getMessage());
        }
    }
}
