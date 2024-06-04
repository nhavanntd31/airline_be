package com.devteria.airline_be.service;

import com.devteria.airline_be.dto.request.RouteRequest;
import com.devteria.airline_be.dto.response.RouteResponse;
import com.devteria.airline_be.entity.Airport;
import com.devteria.airline_be.entity.Route;
import com.devteria.airline_be.exception.AppException;
import com.devteria.airline_be.exception.ErrorCode;
import com.devteria.airline_be.mapper.RouteMapper;
import com.devteria.airline_be.repository.AirportRepository;
import com.devteria.airline_be.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private RouteMapper routeMapper;

    public List<RouteResponse> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map(routeMapper::toRouteResponse)
                .collect(Collectors.toList());
    }

    public Optional<RouteResponse> getRouteById(String id) {
        return routeRepository.findById(id).map(routeMapper::toRouteResponse);
    }

    public RouteResponse createRoute(RouteRequest routeRequestDto) {
        Airport origin = airportRepository.findById(routeRequestDto.getOriginId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        Airport destination = airportRepository.findById(routeRequestDto.getDestinationId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));

        Route route = routeMapper.toRoute(routeRequestDto);
        route.setOrigin(origin);
        route.setDestination(destination);
        route.setCreatedAt(LocalDateTime.now());
        route.setUpdatedAt(LocalDateTime.now());

        return routeMapper.toRouteResponse(routeRepository.save(route));
    }

    public RouteResponse updateRoute(String id, RouteRequest routeRequestDto) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        Airport origin = airportRepository.findById(routeRequestDto.getOriginId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        Airport destination = airportRepository.findById(routeRequestDto.getDestinationId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));

        routeMapper.updateRoute(route, routeRequestDto);
        route.setOrigin(origin);
        route.setDestination(destination);
        route.setUpdatedAt(LocalDateTime.now());

        return routeMapper.toRouteResponse(routeRepository.save(route));
    }

    public void deleteRoute(String id) {
        if (!routeRepository.existsById(id)) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }
        routeRepository.deleteById(id);
    }
}

