package com.devteria.airline_be.dto.response;

import com.devteria.airline_be.entity.Flight;
import com.devteria.airline_be.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    String id;
    Flight seat;
    User user;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
