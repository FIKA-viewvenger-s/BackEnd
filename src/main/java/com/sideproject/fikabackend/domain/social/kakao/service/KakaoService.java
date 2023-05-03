package com.sideproject.fikabackend.domain.social.kakao.service;


import com.sideproject.fikabackend.domain.member.entity.Member;
import com.sideproject.fikabackend.domain.member.repository.MemberRepository;
import com.sideproject.fikabackend.domain.social.kakao.client.KakaoClient;
import com.sideproject.fikabackend.domain.social.kakao.dto.KakaoInfo;
import com.sideproject.fikabackend.domain.social.kakao.dto.KakaoToken;
import com.sideproject.fikabackend.global.jwt.JwtTokenProvider;
import com.sideproject.fikabackend.global.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoClient client;
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;


    @Value("${kakao.auth-url}")
    private String kakaoAuthUrl;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    public KakaoInfo getInfo(final String code, HttpServletResponse response) {

        final KakaoToken token = getToken(code);
        try {
            KakaoInfo clientInfo = client.getInfo(new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken());
            String password = clientInfo.getKakaoAccount().getEmail();
            String memberEmail = clientInfo.getKakaoAccount().getEmail();
            String nickname = clientInfo.getKakaoAccount().getProfile().getNickname();
            String encodedPassword = passwordEncoder.encode(password);

            Member kakaoMember = memberRepository.findByMemberEmail(memberEmail)
                    .orElse(null);
            if(kakaoMember == null){
                Member members = new Member(memberEmail, nickname, encodedPassword);
                memberRepository.save(members);
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberEmail, encodedPassword);

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
            return clientInfo;
        } catch (Exception e) {
            log.error("something error..", e);
            return KakaoInfo.fail();
        }
    }

    private KakaoToken getToken(final String code) {
        try {
            return client.getToken(new URI(kakaoAuthUrl), restapiKey, redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("Something error..", e);

            return KakaoToken.fail();
        }
    }
}
