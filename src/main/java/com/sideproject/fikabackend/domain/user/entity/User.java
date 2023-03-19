package com.sideproject.fikabackend.domain.user.entity;

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
@Table(name = "users")
public class User extends Timestamped implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNm;

    @Column(nullable = true)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(nullable = true)
    private String profileImg;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = true)
    private Long userName;

    @Column(nullable = true)
    private String nickName;

    @Column(nullable = true)
    private String region;

    @Column(nullable = true)
    private Long age;

    ////////////////////////////////////////////// 회원 가입 이후 받는 정보
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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Status> status = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_nm",nullable = true)
    private Team team;

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
        return userId;
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
