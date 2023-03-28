package com.sideproject.fikabackend.domain.member.service;

import com.sideproject.fikabackend.domain.member.dto.LoginReqDto;
import com.sideproject.fikabackend.domain.member.dto.SignUpReqDto;
import com.sideproject.fikabackend.domain.member.entity.Member;
import com.sideproject.fikabackend.domain.member.repository.MemberRepository;
import com.sideproject.fikabackend.global.jwt.JwtTokenProvider;
import com.sideproject.fikabackend.global.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    //회원가입
    @Transactional
    public ResponseEntity<?> signUp(SignUpReqDto signUpReqDto) {
        if (memberRepository.findByMemberId(signUpReqDto.getMemberId()).isPresent()) {
            return ResponseEntity.badRequest().body("중복 Id");
        }
        Member member = new Member(signUpReqDto);

        memberRepository.save(member);

        return ResponseEntity.ok(member);
    }

    // 로그인
    @Transactional
    public ResponseEntity<?> login(LoginReqDto loginReqDto, HttpServletResponse response) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginReqDto.getMemberId(), loginReqDto.getPw());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

//        response.addHeader(JwtTokenProvider.ACCESSTOKEN_HEADER, tokenInfo.getGrantType() + " " + tokenInfo.getAccessToken());
//        response.addHeader(JwtTokenProvider.REFRESHTOKEN_HEADER, tokenInfo.getGrantType() + " " + tokenInfo.getRefreshToken());
        ResponseCookie cookie = ResponseCookie.from("AccessToken",tokenInfo.getAccessToken())
                .maxAge(7*24*60*60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());


        return ResponseEntity.ok("로그인 성공");
    }


}
