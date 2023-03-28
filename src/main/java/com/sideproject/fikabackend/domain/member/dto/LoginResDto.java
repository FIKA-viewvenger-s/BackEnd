package com.sideproject.fikabackend.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResDto {

    private String memberId;
    private String userName;
    private String nickName;
    private String region;
    private int age;
}
