package com.devteria.airline_be.controller;
import com.devteria.airline_be.dto.request.ApiResponse;
import com.devteria.airline_be.dto.request.RouteRequest;
import com.devteria.airline_be.dto.response.RouteResponse;
import com.devteria.airline_be.service.RouteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/routes")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class RouteController {

    RouteService routeService;

    @GetMapping
    public ApiResponse<List<RouteResponse>> getAllRoutes() {
        return ApiResponse.<List<RouteResponse>>builder()
                .result(routeService.getAllRoutes())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<RouteResponse> getRouteById(@PathVariable String id) {
        return ApiResponse.<RouteResponse>builder()
                .result(routeService.getRouteById(id))
                .build();
    }

    @PostMapping

    public ApiResponse<RouteResponse> createRoute(@RequestBody RouteRequest routeRequest) {
        RouteResponse createdRoute = routeService.createRoute(routeRequest);
        return ApiResponse.<RouteResponse>builder()
                .result(createdRoute)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<RouteResponse> updateRoute(@PathVariable String id, @RequestBody RouteRequest routeRequest) {
        RouteResponse updatedRoute = routeService.updateRoute(id, routeRequest);
        return ApiResponse.<RouteResponse>builder()
                .result(updatedRoute)
                .build();
    }

//    @DeleteMapping("/{id}")
//    public String deleteRoute(@PathVariable String id) {
//        routeService.deleteRoute(id);
//        return "Delete successfully";
//    }
}
