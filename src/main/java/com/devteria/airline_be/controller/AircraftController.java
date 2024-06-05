package com.devteria.airline_be.controller;

import com.devteria.airline_be.dto.request.AircraftRequest;
import com.devteria.airline_be.dto.request.ApiResponse;
import com.devteria.airline_be.dto.response.AircraftResponse;
import com.devteria.airline_be.service.AircraftService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/aircrafts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AircraftController {
    AircraftService aircraftService;

    @GetMapping
    public ApiResponse<List<AircraftResponse>> getAllAircrafts() {
            return ApiResponse.<List<AircraftResponse>>builder()
                    .result(aircraftService.getAllAircraft())
                    .build();

    }

    @GetMapping("/{id}")
    public ApiResponse<AircraftResponse> getAircraftById(@PathVariable String id) {
        return ApiResponse.<AircraftResponse>builder()
                .result(aircraftService.getAircraftById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<AircraftResponse> createAircraft(@RequestBody AircraftRequest request) {
        return ApiResponse.<AircraftResponse>builder()
                .result(aircraftService.createAircraft(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<AircraftResponse> updateAircraft(@PathVariable String id, @RequestBody AircraftRequest request) {
        return ApiResponse.<AircraftResponse>builder()
                .result(aircraftService.updateAircraft(id,request))
                .build();
    }
}
