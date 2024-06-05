package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.AircraftRequest;
import com.devteria.airline_be.dto.response.AircraftResponse;
import com.devteria.airline_be.entity.Aircraft;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AircraftMapper {
    Aircraft toAircraft (AircraftRequest request);
    AircraftResponse toAircraftResponse(Aircraft aircraft);
    void updateAircraft(@MappingTarget Aircraft aircraft, AircraftRequest request);

}
