package com.devteria.airline_be.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 6, message = "Username must be at least 6 characters")
    String username;

    @NotBlank(message = "Name is required")
    String firstname;

    @NotBlank(message = "Name is required")
    String lastname;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    String email;
    List<String> roles ;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 8 characters")
    String password;

    String avatarUrl;

}
