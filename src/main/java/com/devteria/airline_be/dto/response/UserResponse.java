package com.devteria.airline_be.dto.response;

import com.devteria.airline_be.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String name;
    String email;
    String avatarUrl;
    User.Role role;
    User.Status status;
    Boolean isVerified;
    LocalDateTime lastLogin;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}