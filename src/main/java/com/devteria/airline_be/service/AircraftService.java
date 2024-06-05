package com.devteria.airline_be.service;

import com.devteria.airline_be.dto.request.AircraftRequest;
import com.devteria.airline_be.dto.response.AircraftResponse;
import com.devteria.airline_be.entity.Aircraft;
import com.devteria.airline_be.exception.AppException;
import com.devteria.airline_be.exception.ErrorCode;
import com.devteria.airline_be.mapper.AircraftMapper;
import com.devteria.airline_be.repository.AircraftRepository;
import com.devteria.airline_be.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AircraftService {

    AircraftRepository aircraftRepository;

    UserRepository userRepository;

    AircraftMapper aircraftMapper;

    //get all
    public List<AircraftResponse> getAllAircraft() {
        return aircraftRepository.findAll().stream()
                .map(aircraftMapper::toAircraftResponse)
                .collect(Collectors.toList());
    }

    //get by id
    public AircraftResponse getAircraftById(String id) {
        return aircraftMapper.toAircraftResponse(aircraftRepository.findById(id).orElseThrow(
                ()-> new AppException(ErrorCode.AIRCRAFT_NOT_EXISTED)));


    }

    //create
    public AircraftResponse createAircraft(AircraftRequest aircraftRequest) {
        if(aircraftRepository.existsByNumber(aircraftRequest.getNumber()))
            throw new AppException(ErrorCode.AIRCRAFT_EXISTED);
        Aircraft aircraft = aircraftMapper.toAircraft(aircraftRequest);
        aircraft.setCreatedAt(LocalDateTime.now());
        aircraft.setUpdatedAt(LocalDateTime.now());
        return aircraftMapper.toAircraftResponse(aircraftRepository.save(aircraft));
    }

    //update
    public AircraftResponse updateAircraft(String id,AircraftRequest aircraftRequest) {
        Aircraft aircraft = aircraftRepository.findById(id).orElseThrow(
                ()-> new AppException(ErrorCode.AIRCRAFT_NOT_EXISTED));
        aircraftMapper.updateAircraft(aircraft,aircraftRequest);
        return aircraftMapper.toAircraftResponse(aircraftRepository.save(aircraft));
    }



}
