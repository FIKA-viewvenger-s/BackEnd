package com.sideproject.fikabackend.domain.member.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignUpReqDto {
    private String memberEmail;
    private String memberPw;
    private String memberNm;
    private String nickName;
}
