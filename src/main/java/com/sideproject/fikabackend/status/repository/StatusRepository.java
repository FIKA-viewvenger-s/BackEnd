package com.sideproject.fikabackend.status.repository;


import com.sideproject.fikabackend.status.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
