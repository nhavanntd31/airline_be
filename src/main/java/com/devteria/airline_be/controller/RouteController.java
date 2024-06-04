package com.devteria.airline_be.controller;
import com.devteria.airline_be.dto.request.ApiResponse;
import com.devteria.airline_be.dto.request.RouteRequest;
import com.devteria.airline_be.dto.response.RouteResponse;
import com.devteria.airline_be.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RouteResponse>>> getAllRoutes() {
        List<RouteResponse> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Routes fetched successfully", routes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RouteResponse>> getRouteById(@PathVariable String id) {
        Optional<RouteResponse> route = routeService.getRouteById(id);
        return route.map(value -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Route fetched successfully", value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Route not found", null)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RouteResponse>> createRoute(@RequestBody RouteRequest routeRequestDto) {
        RouteResponse createdRoute = routeService.createRoute(routeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Route created successfully", createdRoute));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RouteResponse>> updateRoute(@PathVariable String id, @RequestBody RouteRequest routeRequestDto) {
        RouteResponse updatedRoute = routeService.updateRoute(id, routeRequestDto);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Route updated successfully", updatedRoute));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRoute(@PathVariable String id) {
        routeService.deleteRoute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Route deleted successfully", null));
    }
}
