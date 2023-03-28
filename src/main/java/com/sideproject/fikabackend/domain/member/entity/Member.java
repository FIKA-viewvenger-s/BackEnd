package com.sideproject.fikabackend.domain.member.entity;

import com.sideproject.fikabackend.domain.member.dto.SignUpReqDto;
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

    @Column(nullable = true)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private String memberId;
    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String region;

<<<<<<< HEAD:src/main/java/com/sideproject/fikabackend/domain/user/entity/Member.java
<<<<<<< HEAD:src/main/java/com/sideproject/fikabackend/domain/user/entity/User.java
<<<<<<< HEAD
    @Column
=======
=======

>>>>>>> bd6226a (Fix: 디렉토리 정리):src/main/java/com/sideproject/fikabackend/domain/user/entity/Member.java
    @Column(nullable = true)
>>>>>>> 4e871a5 (Fix: 로그인response 1차 완료)
=======
    @Column(nullable = false)
>>>>>>> fd45d75 (Add: 회원가입 로직 추가):src/main/java/com/sideproject/fikabackend/domain/member/entity/Member.java
    private Long age;

    ////////////////////////////////////////////// 회원 가입 이후 받는 정보
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

    public Member(SignUpReqDto signUpReqDto){
        this.memberId = signUpReqDto.getMemberId();
        this.pw = signUpReqDto.getPw();
        this.userName = signUpReqDto.getUserName();
        this.nickName = signUpReqDto.getNickName();
        this.region = signUpReqDto.getRegion();
        this.age = signUpReqDto.getAge();
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
