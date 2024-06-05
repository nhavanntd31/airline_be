package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.AirportRequest;
import com.devteria.airline_be.dto.response.AirportResponse;
import com.devteria.airline_be.entity.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    Airport toAirport(AirportRequest request);
    AirportResponse toAirportResponse(Airport airport);
    void updateAirport(@MappingTarget Airport airport, AirportRequest request);
}
