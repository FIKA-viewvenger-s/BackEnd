package com.sideproject.fikabackend.domain.social.kakao.service;


import com.sideproject.fikabackend.domain.member.entity.Member;
import com.sideproject.fikabackend.domain.social.kakao.client.KakaoClient;
import com.sideproject.fikabackend.domain.social.kakao.dto.KakaoInfo;
import com.sideproject.fikabackend.domain.social.kakao.dto.KakaoToken;
import com.sideproject.fikabackend.domain.social.kakao.repository.KakaoClientRepository;
import com.sideproject.fikabackend.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoClient client;
    private final KakaoClientRepository kakaoClientRepository;


    @Value("${kakao.auth-url}")
    private String kakaoAuthUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    public KakaoInfo getInfo(final String code, HttpServletResponse response) {

        final KakaoToken token = getToken(code);
        response.addHeader(JwtTokenProvider.ACCESSTOKEN_HEADER,token.getTokenType() + " " +  token.getAccessToken());
        response.addHeader(JwtTokenProvider.REFRESHTOKEN_HEADER,token.getTokenType() + " " + token.getRefreshToken());
        log.debug("token = {}", token);
        try {
            KakaoInfo clientInfo = client.getInfo(new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken());
            Member member = new Member(clientInfo);
            kakaoClientRepository.save(member);
            return clientInfo;
        } catch (Exception e) {
            log.error("something error..", e);
            return KakaoInfo.fail();
        }
    }

    private KakaoToken getToken(final String code) {
        try {
            return client.getToken(new URI(kakaoAuthUrl), restapiKey, redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("Something error..", e);

            return KakaoToken.fail();
        }
    }
}
