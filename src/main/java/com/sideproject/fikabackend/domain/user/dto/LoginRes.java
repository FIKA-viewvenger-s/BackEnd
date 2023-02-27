package com.sideproject.fikabackend.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRes {

    private String uId;
    private String userName;
    private String nickName;
    private String region;
    private int age;
}
