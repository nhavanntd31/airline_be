package com.devteria.airline_be.service;

import com.devteria.airline_be.dto.request.AirportRequest;
import com.devteria.airline_be.dto.response.AirportResponse;
import com.devteria.airline_be.entity.Airport;
import com.devteria.airline_be.exception.AppException;
import com.devteria.airline_be.exception.ErrorCode;
import com.devteria.airline_be.mapper.AirportMapper;
import com.devteria.airline_be.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportMapper airportMapper;

    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(airportMapper::toAirportResponse)
                .collect(Collectors.toList());
    }

    public Optional<AirportResponse> getAirportById(String id) {
        return airportRepository.findById(id).map(airportMapper::toAirportResponse);
    }

    public AirportResponse createAirport(AirportRequest airportRequest) {
        Airport airport = airportMapper.toAirport(airportRequest);
        airport.setCreatedAt(LocalDateTime.now());
        airport.setUpdatedAt(LocalDateTime.now());

        return airportMapper.toAirportResponse(airportRepository.save(airport));
    }

    public AirportResponse updateAirport(String id, AirportRequest airportRequest) {
        Airport airport = airportRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        airportMapper.updateAirport(airport, airportRequest);
        airport.setUpdatedAt(LocalDateTime.now());

        return airportMapper.toAirportResponse(airportRepository.save(airport));
    }

    public void deleteAirport(String id) {
        if (!airportRepository.existsById(id)) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        airportRepository.deleteById(id);
    }
}
