package com.devteria.airline_be.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteRequest{
    String originId;
    String destinationId;
    Boolean isRoundTrip;
}
