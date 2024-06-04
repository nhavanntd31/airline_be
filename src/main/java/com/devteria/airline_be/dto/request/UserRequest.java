package com.devteria.airline_be.dto.request;

import com.devteria.airline_be.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank(message = "Name is required")
    String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;

    String avatarUrl;

    @NotNull(message = "Role is required")
    User.Role role;

    @NotNull(message = "Status is required")
    User.Status status;
}
