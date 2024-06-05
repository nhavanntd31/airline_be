package com.devteria.airline_be.dto.request;

import com.devteria.airline_be.entity.Flight;
import com.devteria.airline_be.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    Flight seat;
    User user;
    String reservationCode;
}
