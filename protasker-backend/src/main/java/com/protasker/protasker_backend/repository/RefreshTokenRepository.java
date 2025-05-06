package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.RefreshToken;
import com.protasker.protasker_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByToken(String token);
}

