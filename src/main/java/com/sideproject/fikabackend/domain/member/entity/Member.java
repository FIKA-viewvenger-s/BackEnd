package com.sideproject.fikabackend.domain.member.entity;

import com.sideproject.fikabackend.domain.member.dto.SignUpReqDto;
import com.sideproject.fikabackend.domain.social.kakao.dto.KakaoInfo;

import com.sideproject.fikabackend.domain.status.entity.Status;
import com.sideproject.fikabackend.domain.team.entity.Team;
import com.sideproject.fikabackend.global.util.Timestamped;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
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
    private Long memberNm;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column
    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    // 카카오에서는 email
    @Column(nullable = false)
    private String memberId;


    ////////////////////////////////////////////// 회원 가입 이후 받는 정보 ?
    @Column
    private String pw;

    @Column
    private String userName;

    @Column
    private String nickName;
    @Column
    private String region;

    @Column
    private Long age;

    @Column
    private String profileImg;

    @Column
    private Long mannerPnt;

    @Column
    private Long reCode;

    @Column
    private String position;

    @Column
    private String tier;

    @Column
    private String odds;

    //스탯번호
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Status> status = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_nm",nullable = true)
    private Team team;


    // 일반 로그인
    public Member(SignUpReqDto signUpReqDto){
        this.memberId = signUpReqDto.getMemberId();
        this.pw = signUpReqDto.getPw();
        this.userName = signUpReqDto.getUserName();
        this.nickName = signUpReqDto.getNickName();
        this.region = signUpReqDto.getRegion();
        this.age = signUpReqDto.getAge();
    }

    // 카카오 로그인
//    public Member(KakaoInfo clientInfo) {
//        this.memberId = clientInfo.getKakaoAccount().getEmail();
//        this.userName = clientInfo.getKakaoAccount().getProfile().getNickname();
//        this.socialType = SocialType.KAKAO;
//    }

    public Member(String memberId, String nickname, String encodedPassword) {
        this.memberId = memberId;
        this.nickName = nickname;
        this.pw = encodedPassword;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    //    @Builder.Default
    private List<String> roles = new ArrayList<>();




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public String getPassword() {
        return pw;
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
