package com.devteria.airline_be.dto.request;

import com.devteria.airline_be.entity.Flight;
import com.devteria.airline_be.entity.Ticket;
import com.devteria.airline_be.entity.User;
import com.devteria.airline_be.service.UserService;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentRequest {
    Ticket ticket;
    User user;
}
