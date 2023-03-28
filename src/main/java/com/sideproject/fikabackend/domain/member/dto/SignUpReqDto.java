package com.sideproject.fikabackend.domain.member.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpReqDto {
    private String memberId;
    private String pw;
    private String userName;
    private String nickName;
    private String region;
    private Long age;
}
