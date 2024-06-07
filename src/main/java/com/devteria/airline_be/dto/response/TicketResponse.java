package com.devteria.airline_be.dto.response;

import com.devteria.airline_be.entity.Flight;
import com.devteria.airline_be.entity.Ticket;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketResponse {
    String id;
    Flight flight;
    String type;
    int price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
