package com.sideproject.fikabackend.domain.social.kakao.repository;


import com.sideproject.fikabackend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoClientRepository extends JpaRepository<Member, Long> {

}