package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.UserRequest;
import com.devteria.airline_be.dto.response.UserResponse;
import com.devteria.airline_be.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserRequest userRequest);
    UserResponse toUserResponse(User user);
}
