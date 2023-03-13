package com.sideproject.fikabackend.domain.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpReqDto {

    private String uId;
    private String pw;
    private Long userName;
    private String nickName;
    private String region;
    private int age;
}
