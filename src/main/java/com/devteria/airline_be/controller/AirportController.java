package com.devteria.airline_be.controller;

import com.devteria.airline_be.dto.request.AirportRequest;
import com.devteria.airline_be.dto.request.ApiResponse;
import com.devteria.airline_be.dto.response.AirportResponse;
import com.devteria.airline_be.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("airports")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AirportResponse>>> getAllAirports() {
        List<AirportResponse> airports = airportService.getAllAirports();
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Airports fetched successfully", airports));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AirportResponse>> getAirportById(@PathVariable String id) {
        Optional<AirportResponse> airport = airportService.getAirportById(id);
        return airport.map(value -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Airport fetched successfully", value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Airport not found", null)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AirportResponse>> createAirport(@RequestBody AirportRequest airportRequest) {
        AirportResponse createdAirport = airportService.createAirport(airportRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Airport created successfully", createdAirport));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AirportResponse>> updateAirport(@PathVariable String id, @RequestBody AirportRequest airportRequest) {
        AirportResponse updatedAirport = airportService.updateAirport(id, airportRequest);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Airport updated successfully", updatedAirport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAirport(@PathVariable String id) {
        airportService.deleteAirport(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "Airport deleted successfully", null));
    }
}
