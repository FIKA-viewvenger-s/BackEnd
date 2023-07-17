package com.sideproject.fikabackend.domain.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sideproject.fikabackend.domain.chat.model.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

/**
 * ChatService는 chatRooms를 클래스 변수로 갖는데, chatRooms는 RoomId를 key로 갖고 chatRoom을 value로 갖는 Map으로,
 * createRoom() 메서드가 실행되면 새로 방이 만들어지고 chatRooms에 chatRoom이 추가된다. 방의 아이디는 UUID로 랜덤으로 생성하여 지정되며,
 * roomId를 사용해 findRoomById() 메서드를 통해서 해당 채팅방을 불러올 수 있다.
 * sendMessage() 메서드는 TALK 상태일 경우 실행되는 메서드로, 메시지를 해당 채팅방의 webSocket 세션에 보내는 메서드이다.
 *
 **/
@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name) {
        String randomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();
        chatRooms.put(randomId, chatRoom);
        return chatRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
