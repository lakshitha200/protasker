package com.protasker.protasker_backend.repository;

import com.protasker.protasker_backend.model.AgileModel.ProductBacklogItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBacklogItemRepository extends JpaRepository<ProductBacklogItem, Long> {}
