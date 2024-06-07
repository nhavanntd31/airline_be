package com.devteria.airline_be.controller;

import com.devteria.airline_be.dto.request.ApiResponse;
import com.devteria.airline_be.dto.request.UserCreateRequest;
import com.devteria.airline_be.dto.request.UserUpdateRequest;
import com.devteria.airline_be.dto.response.UserResponse;
import com.devteria.airline_be.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;


    @PostMapping("/create")
    ApiResponse<UserResponse> createUser(@Valid @RequestBody UserCreateRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ApiResponse.<UserResponse>builder()
                .result(userResponse)
                .build();
    }

    @GetMapping("/{id}")
   ApiResponse<UserResponse> getUserById(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(id))
                .build();
    }

    @GetMapping("/myinfo")
    ApiResponse<UserResponse> getUserById() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<UserResponse> updateUser(@PathVariable String id, @Valid @RequestBody UserUpdateRequest userRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id,userRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "Delete successfully!";
    }
}
