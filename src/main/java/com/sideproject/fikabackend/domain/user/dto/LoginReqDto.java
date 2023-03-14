package com.sideproject.fikabackend.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginReqDto {
    private String userId;
    private String pw;
}
