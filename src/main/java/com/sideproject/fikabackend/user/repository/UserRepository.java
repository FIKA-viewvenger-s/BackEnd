package com.sideproject.fikabackend.user.repository;


import com.sideproject.fikabackend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
