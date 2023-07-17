package com.sideproject.fikabackend.domain.chat.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 스프링 부트에서 webSocket을 활성화 하려면, WebSocketConfig 클래스를 만들어
 * 클라이언트가 보내는 정보를 받아서 처리할 Handler를 만들어주고,
 * 이를 연결할 웹소켓 주소를 설정해 주어야한다.
 *
 * @EnableWebSocket을 통해 webSocket을 활성화 해주고, WebSocektConfigurer 인터페이스를 적용하여
 * WebSocketConfig라는 클래스를 만들어 이를 설정해준다.
 * 우리가 정보를 처리할 Handler와 webSocket 주소를 WebSocketHandlerRegistry에 추가해주면,
 * 해당 주소로 접근하면 웹소켓 연결을 할 수가 있다.
 **/


@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "ws/chat").setAllowedOrigins("*");
    }
}
