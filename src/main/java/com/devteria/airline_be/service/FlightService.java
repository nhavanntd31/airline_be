package com.devteria.airline_be.service;

import com.devteria.airline_be.dto.request.FlightRequest;
import com.devteria.airline_be.dto.response.FlightResponse;
import com.devteria.airline_be.entity.Aircraft;
import com.devteria.airline_be.entity.Flight;
import com.devteria.airline_be.entity.Route;
import com.devteria.airline_be.exception.AppException;
import com.devteria.airline_be.exception.ErrorCode;
import com.devteria.airline_be.mapper.FlightMapper;
import com.devteria.airline_be.repository.AircraftRepository;
import com.devteria.airline_be.repository.FlightRepository;
import com.devteria.airline_be.repository.RouteRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FlightService {

    FlightRepository flightRepository;

    RouteRepository routeRepository;

    AircraftRepository aircraftRepository;

    FlightMapper flightMapper;

    public List<FlightResponse> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toFlightResponse)
                .collect(Collectors.toList());
    }

    public FlightResponse getFlightById(String id) {
        return flightMapper.toFlightResponse(flightRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.FLIGHT_NOT_EXISTED)));
    }

    public FlightResponse createFlight(FlightRequest flightRequest) {
        Route route = routeRepository.findById(flightRequest.getRoute().getId())
                .orElseThrow(() -> new AppException(ErrorCode.ROUTE_NOT_EXISTED));
        Aircraft aircraft = aircraftRepository.findById(flightRequest.getAircraft().getId())
                .orElseThrow(() -> new AppException(ErrorCode.AIRCRAFT_NOT_EXISTED));

        Flight flight = flightMapper.toFlight(flightRequest);
        flight.setRoute(route);
        flight.setAircraft(aircraft);
        flight.setCreatedAt(LocalDateTime.now());
        flight.setUpdatedAt(LocalDateTime.now());

        return flightMapper.toFlightResponse(flightRepository.save(flight));
    }

    public FlightResponse updateFlight(String id, FlightRequest flightRequest) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.FLIGHT_NOT_EXISTED));
        Route route = routeRepository.findById(flightRequest.getRoute().getId())
                .orElseThrow(() -> new AppException(ErrorCode.ROUTE_NOT_EXISTED));
        Aircraft aircraft = aircraftRepository.findById(flightRequest.getAircraft().getId())
                .orElseThrow(() -> new AppException(ErrorCode.AIRCRAFT_NOT_EXISTED));

        flightMapper.updateFlight(flight, flightRequest);
        flight.setRoute(route);
        flight.setAircraft(aircraft);
        flight.setUpdatedAt(LocalDateTime.now());

        return flightMapper.toFlightResponse(flightRepository.save(flight));
    }

    public void deleteFlight(String id) {
        if (!flightRepository.existsById(id)) {
            throw new AppException(ErrorCode.FLIGHT_NOT_EXISTED);
        }
        flightRepository.deleteById(id);
    }
}
