package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByClientId(String clientId);
}
