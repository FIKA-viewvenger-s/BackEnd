package com.sideproject.fikabackend.domain.social.google.controller;

import com.sideproject.fikabackend.domain.member.entity.SocialType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Members", description = "유저 관련 API")
@RequestMapping("members")
@Controller
public class LoginController {

    private final String googleAuthUrl;
    private final String kakaoAuthUrl;

    public LoginController(@Value("${api.google.auth_url}") String googleAuthUrl,
                           @Value("${kakao.code_url}") String kakaoAuthUrl) {
        this.googleAuthUrl = googleAuthUrl;
        this.kakaoAuthUrl = kakaoAuthUrl;
    }

    @Operation(summary = "로그인 요청", description = "소셜 로그인을 하기 위한 api 입니다.", tags = {"Members"})
    @GetMapping("/login")
    public String getAuthCode(@RequestParam("socialType") String socialType) {
        String authUrl;

        if (socialType.equals(SocialType.GOOGLE.getValue())) {
            authUrl = googleAuthUrl;
        } else if (socialType.equals(SocialType.KAKAO.getValue())) {
            authUrl = kakaoAuthUrl;
        } else {
            throw new IllegalArgumentException("소셜 타입이 잘못되었습니다.");
        }

        return "redirect:" + authUrl;
    }
}
