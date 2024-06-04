package com.devteria.airline_be.controller;

import com.devteria.airline_be.dto.request.ApiResponse;
import com.devteria.airline_be.dto.request.UserRequest;
import com.devteria.airline_be.dto.response.UserResponse;
import com.devteria.airline_be.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(userRequest))
                .build();
    }

    @GetMapping("/{id}")
   ApiResponse<UserResponse> getUserById(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(id))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<UserResponse> updateUser(@PathVariable String id, @Valid @RequestBody UserRequest userRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteUser(@PathVariable String id) {

    }
}
