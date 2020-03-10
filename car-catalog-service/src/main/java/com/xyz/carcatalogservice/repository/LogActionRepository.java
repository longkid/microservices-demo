package com.xyz.carcatalogservice.repository;

import com.xyz.carcatalogservice.entity.LogAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogActionRepository extends JpaRepository<LogAction, Long> {
}
