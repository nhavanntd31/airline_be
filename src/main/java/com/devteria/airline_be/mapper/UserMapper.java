// package com.devteria.airline_be.mapper;

// import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
// import org.mapstruct.MappingTarget;

// import com.devteria.airline_be.dto.request.UserCreationRequest;
// import com.devteria.airline_be.dto.request.UserUpdateRequest;
// import com.devteria.airline_be.dto.response.UserResponse;
// import com.devteria.airline_be.entity.User;

// @Mapper(componentModel = "spring")
// public interface UserMapper {
//     User toUser(UserCreationRequest request);

//     UserResponse toUserResponse(User user);

//     @Mapping(target = "roles", ignore = true)
//     void updateUser(@MappingTarget User user, UserUpdateRequest request);
// }
