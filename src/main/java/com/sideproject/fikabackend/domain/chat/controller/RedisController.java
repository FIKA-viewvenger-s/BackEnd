package com.sideproject.fikabackend.domain.chat.controller;

import com.sideproject.fikabackend.domain.chat.model.ChatMessage;
import com.sideproject.fikabackend.domain.chat.service.RedisPubService;
import com.sideproject.fikabackend.domain.chat.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RedisController {

    private final RedisService redisService;
    private final RedisPubService redisPubService;

    @GetMapping("/chat/redis")
    public String test() {
        redisService.redisString();

        return "success";
    }

    @PostMapping("api/chat")
    public String pubSub(@RequestBody ChatMessage chatMessage) {
        //메시지 보내기
        redisPubService.sendMessage(chatMessage);

        return "success";
    }

}
