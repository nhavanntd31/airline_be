package com.devteria.airline_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteResponse {
    String id;
    String originId;
    String destinationId;
    Boolean isRoundTrip;
}
