package com.devteria.airline_be.repository;

import com.devteria.airline_be.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, String> {
    boolean existsByNumber(String number);

}
