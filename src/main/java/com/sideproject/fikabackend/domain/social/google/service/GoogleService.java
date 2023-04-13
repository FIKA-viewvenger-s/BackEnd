package com.sideproject.fikabackend.domain.social.google.service;

import com.sideproject.fikabackend.domain.social.google.client.GoogleClient;
import com.sideproject.fikabackend.domain.social.google.dto.GoogleAccount;
import com.sideproject.fikabackend.domain.social.google.dto.GoogleToken;
import com.sideproject.fikabackend.global.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class GoogleService {

    static final String GRANT_TYPE = "authorization_code";

    @Value("${api.google.client_id}")
    private String clientId;
    @Value("${api.google.client_secret}")
    private String clientSecret;
    @Value("${api.google.redirect_uri}")
    private String redirectUri;

    private final GoogleClient googleClient;

    public GoogleService(GoogleClient googleClient) {
        this.googleClient = googleClient;
    }

    public GoogleAccount getInfo(final String code, HttpServletResponse response) {
        final GoogleToken token = getToken(code);
        log.info("토큰 정보 : {}", token.toString());

        response.addHeader(JwtTokenProvider.ACCESSTOKEN_HEADER,token.getTokenType() + " " +  token.getAccessToken());
        response.addHeader(JwtTokenProvider.REFRESHTOKEN_HEADER,token.getTokenType() + " " + token.getRefreshToken());

        try {
            return googleClient.getInfo(token.getIdToken());
        } catch (Exception e) {
            log.error("google getInfo() error ", e);
            e.printStackTrace();
            return new GoogleAccount();
        }
    }

    private GoogleToken getToken(final String code) {
        try {
            return googleClient.getToken("0", clientId, clientSecret, code, redirectUri, GRANT_TYPE);
        } catch (Exception e) {
            log.error("google getToken() error ", e);
            e.printStackTrace();
            return new GoogleToken();
        }
    }
}
