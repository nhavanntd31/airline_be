package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.RouteRequest;
import com.devteria.airline_be.dto.response.RouteResponse;
import com.devteria.airline_be.entity.Route;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    Route toRoute(RouteRequest request);
    RouteResponse toRouteResponse(Route route);
    void updateRoute(@MappingTarget Route route, RouteRequest request);
}
