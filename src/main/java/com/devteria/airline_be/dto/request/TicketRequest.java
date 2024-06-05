package com.devteria.airline_be.dto.request;

import com.devteria.airline_be.entity.Flight;
import com.devteria.airline_be.entity.Ticket;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketRequest {
    Flight flight;
    Ticket.Type type;
    int price;
}
