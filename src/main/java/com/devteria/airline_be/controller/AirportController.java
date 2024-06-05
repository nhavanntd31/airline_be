package com.devteria.airline_be.controller;

import com.devteria.airline_be.dto.request.AirportRequest;
import com.devteria.airline_be.dto.request.ApiResponse;
import com.devteria.airline_be.dto.response.AirportResponse;
import com.devteria.airline_be.service.AirportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/airports")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AirportController {

    AirportService airportService;

    @GetMapping
    public ApiResponse<List<AirportResponse>> getAllAirports() {
        return ApiResponse.<List<AirportResponse>>builder()
                .result(airportService.getAllAirports())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<AirportResponse> getAirportById(@PathVariable String id) {
        return ApiResponse.<AirportResponse>builder()
                .result(airportService.getAirportById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<AirportResponse> createAirport(@RequestBody AirportRequest request) {
        return ApiResponse.<AirportResponse>builder()
                .result(airportService.createAirport(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<AirportResponse> updateAirport(@PathVariable String id, @RequestBody AirportRequest request) {
        return ApiResponse.<AirportResponse>builder()
                .result(airportService.updateAirport(id,request))
                .build();
    }

//    @DeleteMapping("/{id}")
//    public String deleteAirport(@PathVariable String id) {
//        airportService.deleteAirport(id);
//        return "Delete successfully";
//    }
}
