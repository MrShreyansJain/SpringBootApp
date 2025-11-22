package com.shreyans.springmodules.repository;

import com.shreyans.springmodules.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepo extends JpaRepository<UserAuthEntity,Long> {

    Optional<UserAuthEntity> findByUsername (String username);
}
