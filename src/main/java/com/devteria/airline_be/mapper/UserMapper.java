package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.UserCreateRequest;
import com.devteria.airline_be.dto.request.UserUpdateRequest;
import com.devteria.airline_be.dto.response.UserResponse;
import com.devteria.airline_be.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    User toUser(UserCreateRequest userRequest);

    UserResponse toUserResponse(User user);
}
