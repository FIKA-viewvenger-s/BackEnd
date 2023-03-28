package com.sideproject.fikabackend.domain.member.controller;

import com.sideproject.fikabackend.domain.member.dto.LoginReqDto;
import com.sideproject.fikabackend.domain.member.dto.SignUpReqDto;
import com.sideproject.fikabackend.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Tag(name = "Users", description = "유저 관련 API")
@RequestMapping("users")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 가입 요청", description = "회원가입을 하기 위한 api 입니다.", tags = {"User"})
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpReqDto signUpReqDto) {
        // todo: 회원가입 비즈니스 로직
        return memberService.signUp(signUpReqDto);
    }

    @Operation(summary = "로그인 요청", description = "로그인을 하기 위한 api 입니다.", tags = {"Users"})
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto loginReqDto, HttpServletResponse response) {
        return memberService.login(loginReqDto, response);
    }


}
