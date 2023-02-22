package com.sideproject.fikabackend.domain.team.repository;

import com.sideproject.fikabackend.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
