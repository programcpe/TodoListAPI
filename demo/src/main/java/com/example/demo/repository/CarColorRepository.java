package com.example.demo.repository;

import com.example.demo.entity.CarColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CarColorRepository extends JpaRepository<CarColor, Long> {
    CarColor findByColorID(Long colorID);
}

