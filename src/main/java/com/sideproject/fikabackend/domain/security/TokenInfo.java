package com.sideproject.fikabackend.domain.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {
    //grantType은 JWT 대한 인증 타입
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
