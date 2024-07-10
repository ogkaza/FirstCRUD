package com.example.TestTask.repo;

import com.example.TestTask.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByName(String name);
}
