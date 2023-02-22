package com.sideproject.fikabackend.user.controller;

import com.sideproject.fikabackend.user.dto.UserRequestDto;
import com.sideproject.fikabackend.user.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Api(value = "User entity api", tags = {"Post"})
public class UserController {

    private final UserRepository userRepository;

    @ApiOperation(value = "회원가입", notes = "회원가입을 하기 위한 api 입니다.")
    @PostMapping("/api/posts")
    public ResponseEntity<?> signupUser(@RequestBody UserRequestDto userRequestDto)  {
        return ResponseEntity.ok("success");

    }
}
