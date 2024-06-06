package com.devteria.airline_be.dto.response;

import com.devteria.airline_be.entity.Aircraft;
import com.devteria.airline_be.entity.Route;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightResponse {
    String id;
    Route route;
    Aircraft aircraft;
    LocalDateTime startTime;
    LocalDateTime endTime;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
