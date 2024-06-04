package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.RouteRequest;
import com.devteria.airline_be.dto.response.RouteResponse;
import com.devteria.airline_be.entity.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "flights", ignore = true)
    Route toRoute(RouteRequest request);

    @Mapping(source = "origin.id", target = "originId")
    @Mapping(source = "destination.id", target = "destinationId")
    RouteResponse toRouteResponse(Route route);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "flights", ignore = true)
    void updateRoute(@MappingTarget Route route, RouteRequest requestDto);
}
