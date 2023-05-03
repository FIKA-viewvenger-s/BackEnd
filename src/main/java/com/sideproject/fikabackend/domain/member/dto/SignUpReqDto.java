package com.sideproject.fikabackend.domain.member.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpReqDto {
    private String mmbrEmail;
    private String mmbrPw;
    private String mmbrNm;
    private String nickName;
}
