package com.sideproject.fikabackend.domain.member.entity;

import com.sideproject.fikabackend.domain.member.dto.SignUpReqDto;
import com.sideproject.fikabackend.domain.social.google.dto.GoogleAccount;
import com.sideproject.fikabackend.global.util.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends Timestamped implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @NotNull(message = "social must not be null")
    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    // 카카오에서는 email
    @NotNull(message = "member-email must not be null")
    private String memberEmail;

    @NotNull(message = "member-password must not be null")
    private String memberPw;

    @NotNull(message = "member-name must not be null")
    private String memberNm;

    @NotNull(message = "member-nickname must not be null")
    private String nickName;
    @NotNull(message = "member-img must not be null")
    private String memberImg;



    // 일반 로그인
    public Member(SignUpReqDto signUpReqDto){
        this.memberEmail = signUpReqDto.getMemberEmail();
        this.memberPw = signUpReqDto.getMemberPw();
        this.memberNm = signUpReqDto.getMemberNm();
        this.nickName = signUpReqDto.getNickName();
    }

    // 카카오 로그인
//    public Member(KakaoInfo clientInfo) {
//        this.memberId = clientInfo.getKakaoAccount().getEmail();
//        this.userName = clientInfo.getKakaoAccount().getProfile().getNickname();
//        this.socialType = SocialType.KAKAO;
//    }

    public Member(String memberEmail, String nickname, String encodedPassword) {
        this.memberEmail = memberEmail;
        this.nickName = nickname;
        this.memberPw = encodedPassword;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    //    @Builder.Default
    private List<String> roles = new ArrayList<>();
    public Member(GoogleAccount clientInfo) {
        this.memberEmail = clientInfo.getEmail();
        this.memberNm = clientInfo.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return memberEmail;
    }

    @Override
    public String getPassword() {
        return memberPw;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
