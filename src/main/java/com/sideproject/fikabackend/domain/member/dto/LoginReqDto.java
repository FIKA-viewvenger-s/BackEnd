package com.sideproject.fikabackend.domain.member.dto;

import lombok.Data;

//@Setter
//@Getter
@Data
public class LoginReqDto {
    private String memberId;
    private String pw;
}
