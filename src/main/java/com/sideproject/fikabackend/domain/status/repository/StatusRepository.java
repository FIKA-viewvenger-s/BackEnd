package com.sideproject.fikabackend.domain.status.repository;


import com.sideproject.fikabackend.domain.status.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
