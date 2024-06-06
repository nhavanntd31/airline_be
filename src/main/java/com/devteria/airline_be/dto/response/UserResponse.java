package com.devteria.airline_be.dto.response;

import com.devteria.airline_be.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String firstname;
    String lastname;
    String email;
    String avatarUrl;
    Set<RoleResponse> roles;
    User.Status status;
    Boolean isVerified;
    LocalDateTime lastLogin;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}