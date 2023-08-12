package com.sideproject.fikabackend.domain.chat.controller;

import com.sideproject.fikabackend.domain.chat.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RedisController {

    private final RedisService redisService;

    @GetMapping("/chat/redis")
    public String test() {
        redisService.redisString();

        return "success";
    }

}
