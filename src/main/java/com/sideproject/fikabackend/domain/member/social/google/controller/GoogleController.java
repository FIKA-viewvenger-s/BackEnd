package com.sideproject.fikabackend.domain.member.social.google.controller;

import com.sideproject.fikabackend.domain.member.social.google.dto.GoogleAccount;
import com.sideproject.fikabackend.domain.member.social.google.service.GoogleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class GoogleController {

    private final GoogleService googleService;

    public GoogleController(GoogleService googleService) {
        this.googleService = googleService;
    }

    /**
     * Authentication code 를 전달 받는 api
     * @param code 인증코드
     * @param response http 응답 값으로 헤더에 토큰 값을 담기 위해 사용한
     * @return GoogleAccount 고객 정보
     */
    @GetMapping("/members/google")
    public GoogleAccount getGoogleAccount(@RequestParam("code") String code, HttpServletResponse response) {
        log.info("google login controller enter : {}", code);
        return googleService.getInfo(code, response);
    }

    /**
     * 토큰 무효화 api
     * @param token 무효화시킬 토큰 값
     * @return ResponseEntity 무효 성공 시 200 응답
     */
    @GetMapping("/member/google/revoke")
    public ResponseEntity<String> revokeGoogleToken(String token) {
        return googleService.revokeToken(token);
    }
}
