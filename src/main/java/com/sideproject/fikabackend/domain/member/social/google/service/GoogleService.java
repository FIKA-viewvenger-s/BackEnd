package com.sideproject.fikabackend.domain.member.social.google.service;

import com.sideproject.fikabackend.domain.member.entity.Member;
import com.sideproject.fikabackend.domain.member.repository.MemberRepository;
import com.sideproject.fikabackend.domain.member.social.google.client.GoogleClient;
import com.sideproject.fikabackend.domain.member.social.google.dto.GoogleAccount;
import com.sideproject.fikabackend.domain.member.social.google.dto.GoogleToken;
import com.sideproject.fikabackend.global.jwt.JwtTokenProvider;
import com.sideproject.fikabackend.global.jwt.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Service
public class GoogleService {

    static final String GRANT_TYPE = "authorization_code";
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final GoogleClient googleClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public GoogleService(@Value("${google.client_id}") String clientId,
                         @Value("${google.client_secret}") String clientSecret,
                         @Value("${google.redirect_uri}") String redirectUri,
                         GoogleClient googleClient, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, MemberRepository memberRepository) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.googleClient = googleClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberRepository = memberRepository;
    }

    public GoogleAccount getInfo(final String code, HttpServletResponse response) {
        final GoogleToken token = getToken(code);
        log.info("토큰 정보 : {}", token.toString());
        try {
            GoogleAccount clientInfo = googleClient.getInfo(token.getIdToken());
            String username = clientInfo.getEmail();
            String password = passwordEncoder.encode(username + "임의의 난수");
            Optional<Member> byMemberId = memberRepository.findByMemberEmail(username);
            if (byMemberId.isEmpty()) {
                Member member = new Member(clientInfo);
                memberRepository.save(member);
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
            TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
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
            log.error("google getInfo() error ", e);
            e.printStackTrace();
            return new GoogleAccount();
        }
    }

    private GoogleToken getToken(final String code) {
        try {
            return googleClient.getToken("0", clientId, clientSecret, code, redirectUri, GRANT_TYPE);
        } catch (Exception e) {
            log.error("google getToken() error ", e);
            e.printStackTrace();
            return new GoogleToken();
        }
    }

    public ResponseEntity<String> revokeToken(String token) {
        String result = googleClient.revokeToken(token);
        return ResponseEntity.ok(result);
    }
}
