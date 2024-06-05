package com.devteria.airline_be.controller;

import com.devteria.airline_be.dto.request.FlightRequest;
import com.devteria.airline_be.dto.request.ApiResponse;
import com.devteria.airline_be.dto.response.FlightResponse;
import com.devteria.airline_be.service.FlightService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FlightController {

    FlightService flightService;

    //List
    @GetMapping
    public ApiResponse<List<FlightResponse>> getAllFlights() {
        List<FlightResponse> flights = flightService.getAllFlights();
        return ApiResponse.<List<FlightResponse>>builder()
                .result(flights)
                .build();
    }

    //Get
    @GetMapping("/{id}")
    public ApiResponse<FlightResponse> getFlightById(@PathVariable String id) {
        FlightResponse flight = flightService.getFlightById(id);
        return ApiResponse.<FlightResponse>builder()
                .result(flight)
                .build();
    }

    //Create
    @PostMapping
    public ApiResponse<FlightResponse> createFlight(@RequestBody FlightRequest flightRequest) {
        FlightResponse createdFlight = flightService.createFlight(flightRequest);
        return ApiResponse.<FlightResponse>builder()
                .result(createdFlight)
                .build();
    }

    //Update
    @PutMapping("/{id}")
    public ApiResponse<FlightResponse> updateFlight(@PathVariable String id, @RequestBody FlightRequest flightRequest) {
        FlightResponse updatedFlight = flightService.updateFlight(id, flightRequest);
        return ApiResponse.<FlightResponse>builder()
                .result(updatedFlight)
                .build();
    }

}
