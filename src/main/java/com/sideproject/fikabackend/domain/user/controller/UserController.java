package com.sideproject.fikabackend.domain.user.controller;

import com.sideproject.fikabackend.domain.user.dto.LoginReq;
import com.sideproject.fikabackend.domain.user.dto.SignUpReq;
import com.sideproject.fikabackend.domain.user.dto.LoginRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "유저 관련 API")
@RequestMapping("users")
@RestController
public class UserController {

    @Operation(summary = "회원 가입 요청", description = "회원가입을 하기 위한 api 입니다.", tags = { "User" })
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    @ApiResponse(responseCode = "404", description = "NOT FOUND")
    @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    @PostMapping
    public ResponseEntity<HttpStatus> signup(@RequestBody SignUpReq signUpReq) {
        // todo: 회원가입 비즈니스 로직
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "로그인 요청", description = "로그인을 하기 위한 api 입니다.", tags = { "User" })
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    @ApiResponse(responseCode = "404", description = "NOT FOUND")
    @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    @PostMapping("login")
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginReq) {
        // todo: 로그인 비즈니스 로직
        LoginRes loginRes = new LoginRes();
        loginRes.setUId("10");
        loginRes.setUserName(loginReq.getUserName());
        loginRes.setNickName("MessiGod");
        loginRes.setRegion("korea");
        loginRes.setAge(72);
        return ResponseEntity.ok(loginRes);
    }
}
