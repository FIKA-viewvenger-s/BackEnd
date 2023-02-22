package com.sideproject.fikabackend.domain.community.repository;

import com.sideproject.fikabackend.domain.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
