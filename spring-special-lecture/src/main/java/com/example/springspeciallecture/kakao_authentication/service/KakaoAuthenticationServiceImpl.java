package com.example.springspeciallecture.kakao_authentication.service;

import com.example.springspeciallecture.account.entity.Account;
import com.example.springspeciallecture.account.entity.LoginType;
import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.account_profile.service.AccountProfileService;
import com.example.springspeciallecture.kakao_authentication.repository.KakaoAuthenticationRepository;
import com.example.springspeciallecture.kakao_authentication.service.response.KakaoLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoAuthenticationServiceImpl implements KakaoAuthenticationService {
    private final KakaoAuthenticationRepository kakaoAuthRepository;
    private final AccountProfileService accountProfileService;

    @Override
    public KakaoLoginResponse handleLogin(String code) {
        Map<String, Object> tokenResponse = kakaoAuthRepository.getAccessToken(code);
        String accessToken = (String) tokenResponse.get("access_token");

        Map<String, Object> userInfo = kakaoAuthRepository.getUserInfo(accessToken);
        String nickname = extractNickname(userInfo);
        String email = extractEmail(userInfo);

        Optional<AccountProfile> optionalProfile =
                accountProfileService.loadProfileByEmailAndLoginType(email, LoginType.KAKAO);

        boolean isNewUser = optionalProfile.isEmpty();
        Long accountId = isNewUser
                ? 0
                : optionalProfile.get().getAccount().getId();

        return KakaoLoginResponse.from(isNewUser, accessToken, nickname, email, accountId);
    }

    private String extractNickname(Map<String, Object> userInfo) {
        return (String) ((Map<?, ?>) userInfo.get("properties")).get("nickname");
    }

    private String extractEmail(Map<String, Object> userInfo) {
        return (String) ((Map<?, ?>) userInfo.get("kakao_account")).get("email");
    }
}
