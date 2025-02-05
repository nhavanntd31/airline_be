package com.devteria.airline_be.dto.response;

import com.devteria.airline_be.entity.Airport;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteResponse {
    String id;
    Airport origin;
    Airport destination;
    Boolean isRoundTrip;
}
