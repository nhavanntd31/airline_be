package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.PermissionRequest;
import com.devteria.airline_be.dto.response.PermissionResponse;
import com.devteria.airline_be.entity.Permission;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}