package com.devteria.airline_be.repository;

import com.devteria.airline_be.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
