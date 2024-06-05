package com.devteria.airline_be.service;

import com.devteria.airline_be.dto.request.RouteRequest;
import com.devteria.airline_be.dto.response.AirportResponse;
import com.devteria.airline_be.dto.response.RouteResponse;
import com.devteria.airline_be.entity.Airport;
import com.devteria.airline_be.entity.Route;
import com.devteria.airline_be.exception.AppException;
import com.devteria.airline_be.exception.ErrorCode;
import com.devteria.airline_be.mapper.RouteMapper;
import com.devteria.airline_be.repository.AirportRepository;
import com.devteria.airline_be.repository.RouteRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RouteService {
    RouteRepository routeRepository;

    AirportRepository airportRepository;

    RouteMapper routeMapper;

    //Get all
    public List<RouteResponse> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map(routeMapper::toRouteResponse)
                .collect(Collectors.toList());
    }


    //Get
    public RouteResponse getRouteById(String id) {
        Route route = routeRepository.findById(id).orElseThrow(
                ()-> new AppException(ErrorCode.ROUTE_NOT_EXISTED)
        );
        return routeMapper.toRouteResponse(route);
    }

    public RouteResponse createRoute(RouteRequest routeRequest) {
        // Tìm Airport dựa trên originId và destinationId từ routeRequest
        Airport origin = airportRepository.findById(routeRequest.getOrigin().getId())
                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED));
        Airport destination = airportRepository.findById(routeRequest.getDestination().getId())
                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED));

        // Kiểm tra nếu đã tồn tại một route với cùng origin và destination
        if (routeRepository.existsByOriginAndDestination(origin, destination)) {
            throw new AppException(ErrorCode.ROUTE_EXISTED);
        }

        // Tạo route mới từ routeRequest
        Route route = routeMapper.toRoute(routeRequest);
        route.setOrigin(origin);  // Thiết lập trường origin
        route.setDestination(destination);  // Thiết lập trường destination
        route.setCreatedAt(LocalDateTime.now());  // Thiết lập thời gian tạo
        route.setUpdatedAt(LocalDateTime.now());  // Thiết lập thời gian cập nhật
//
//        // Lưu route mới và trả về response
        return routeMapper.toRouteResponse(routeRepository.save(route));
    }

    //Update
    public RouteResponse updateRoute(String id, RouteRequest routeRequest) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ROUTE_NOT_EXISTED));
        Airport origin = airportRepository.findById(routeRequest.getOrigin().getId())
                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED));
        Airport destination = airportRepository.findById(routeRequest.getDestination().getId())
                .orElseThrow(() -> new AppException(ErrorCode.AIRPORT_NOT_EXISTED
                ));

        routeMapper.updateRoute(route, routeRequest);
        route.setOrigin(origin);
        route.setDestination(destination);
        route.setUpdatedAt(LocalDateTime.now());

        return routeMapper.toRouteResponse(routeRepository.save(route));
    }

    //Delete
    public void deleteRoute(String id) {
        if (!routeRepository.existsById(id)) {
            throw new AppException(ErrorCode.ROUTE_NOT_EXISTED);
        }
        routeRepository.deleteById(id);
    }
}

