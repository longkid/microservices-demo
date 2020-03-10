package com.xyz.carcatalogservice.repository;

import com.xyz.carcatalogservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
