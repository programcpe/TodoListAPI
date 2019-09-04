package com.example.demo.repository;

import com.example.demo.entity.CarData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CarDataRepository extends JpaRepository<CarData, Long> {
    CarData findByCarID(Long carID);
}
