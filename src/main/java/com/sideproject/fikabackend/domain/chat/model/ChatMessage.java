package com.sideproject.fikabackend.domain.chat.model;

import lombok.Getter;
import lombok.Setter;

/**
 * enum MessageYpe
 * ENTER : 처음 채팅방에 들어오는 상태
 * TALK : 이미 session에 연결되어있고 채팅중인 상태
 *
 * id : roomId
 * sender : 보내는 사람의 닉네임
 * message : 메시지를 담는 변수
 **/
@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK, EXIT
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
