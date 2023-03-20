package com.sideproject.fikabackend.domain.user.controller;

import com.sideproject.fikabackend.domain.user.dto.LoginReqDto;
import com.sideproject.fikabackend.domain.user.dto.SignUpReqDto;
import com.sideproject.fikabackend.domain.user.dto.LoginResDto;
import com.sideproject.fikabackend.domain.user.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Tag(name = "Users", description = "유저 관련 API")
@RequestMapping("users")
@RequiredArgsConstructor
@RestController
public class LoginController {
//
//    @Operation(summary = "회원 가입 요청", description = "회원가입을 하기 위한 api 입니다.", tags = { "User" })
//    @PostMapping
//    public ResponseEntity<HttpStatus> signup(@RequestBody SignUpReqDto signUpReqDto) {
//        // todo: 회원가입 비즈니스 로직
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
//
//    @Operation(summary = "로그인 요청", description = "로그인을 하기 위한 api 입니다.", tags = { "User" })
//    @PostMapping("login")
//    public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
//        // todo: 로그인 비즈니스 로직
//        LoginResDto loginResDto = new LoginResDto();
//        loginResDto.setUId("10");
//        loginResDto.setUserName(loginReqDto.getUId());
//        loginResDto.setNickName("MessiGod");
//        loginResDto.setRegion("korea");
//        loginResDto.setAge(72);
//        return ResponseEntity.ok(loginResDto);
//    }

private final LoginService loginService;

@Operation(summary = "로그인 요청", description = "로그인을 하기 위한 api 입니다.", tags = { "Users" })
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginReqDto loginReqDto, HttpServletResponse response) {
        String username = loginReqDto.getUserId();
        String password = loginReqDto.getPw();
        return loginService.login(username, password, response);
        }


}
