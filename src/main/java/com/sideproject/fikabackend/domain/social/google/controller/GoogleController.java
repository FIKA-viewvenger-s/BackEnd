package com.sideproject.fikabackend.domain.social.google.controller;

import com.sideproject.fikabackend.domain.social.google.dto.GoogleAccount;
import com.sideproject.fikabackend.domain.social.google.service.GoogleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class GoogleController {

    private final GoogleService googleService;

    public GoogleController(GoogleService googleService) {
        this.googleService = googleService;
    }

    @GetMapping("/member/google")
    public GoogleAccount getGoogleAccount(@RequestParam("code") String code, HttpServletResponse response) {
        return googleService.getInfo(code, response);
    }
}
