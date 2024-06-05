package com.devteria.airline_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AircraftResponse {
    String id;
    String type;
    String number;
    Integer seats;
}
