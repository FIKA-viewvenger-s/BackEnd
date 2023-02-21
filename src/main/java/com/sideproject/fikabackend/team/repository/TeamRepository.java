package com.sideproject.fikabackend.team.repository;

import com.sideproject.fikabackend.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
