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
public class BookingResponse {
    String id;
    String seat;
    String user;
    String user_id;
    String reservationCode;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
