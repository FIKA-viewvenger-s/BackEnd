package com.sideproject.fikabackend.domain.member.repository;


import com.sideproject.fikabackend.domain.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findBymmbrEmail(String mmbrEmail);
}
