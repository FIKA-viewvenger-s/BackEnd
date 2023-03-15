package com.sideproject.fikabackend.domain.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Setter
//@Getter
@Data
public class LoginReqDto {
    private String userId;
    private String pw;
}
