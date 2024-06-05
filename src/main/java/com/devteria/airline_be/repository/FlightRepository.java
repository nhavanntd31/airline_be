package com.devteria.airline_be.repository;

import com.devteria.airline_be.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
}
