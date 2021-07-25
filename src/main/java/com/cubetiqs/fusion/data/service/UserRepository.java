package com.cubetiqs.fusion.data.service;

import com.cubetiqs.fusion.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}