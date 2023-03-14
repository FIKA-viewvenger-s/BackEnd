package com.sideproject.fikabackend.domain.security;

import com.sideproject.fikabackend.domain.user.dto.LoginReqDto;
import com.sideproject.fikabackend.domain.user.entity.UserRole;
import com.sideproject.fikabackend.domain.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final LoginService loginService;


    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginReqDto loginReqDto) {
        String userId = loginReqDto.getUserId();
        String pw = loginReqDto.getPw();
        TokenInfo tokenInfo = loginService.login(userId, pw);
        return tokenInfo;
    }
}
