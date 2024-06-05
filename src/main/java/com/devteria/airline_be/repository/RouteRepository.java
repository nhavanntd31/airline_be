package com.devteria.airline_be.repository;

import com.devteria.airline_be.entity.Airport;
import com.devteria.airline_be.entity.Route;
import com.devteria.airline_be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, String> {
    boolean existsByOriginAndDestination(Airport origin, Airport destination);

}
