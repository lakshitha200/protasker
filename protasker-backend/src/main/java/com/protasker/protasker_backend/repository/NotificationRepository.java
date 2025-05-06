package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
