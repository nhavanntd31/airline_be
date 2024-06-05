package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.FlightRequest;
import com.devteria.airline_be.dto.response.FlightResponse;
import com.devteria.airline_be.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    Flight toFlight(FlightRequest request);

    FlightResponse toFlightResponse(Flight flight);

    void updateFlight(@MappingTarget Flight flight, FlightRequest request);
}
