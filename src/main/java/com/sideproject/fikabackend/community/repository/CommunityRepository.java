package com.sideproject.fikabackend.community.repository;

import com.sideproject.fikabackend.community.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
