package com.devteria.airline_be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devteria.airline_be.dto.request.RoleRequest;
import com.devteria.airline_be.dto.response.RoleResponse;
import com.devteria.airline_be.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
