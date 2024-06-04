package com.devteria.airline_be.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AirportResponse {
    String id;
    String name;
    String address;
    Boolean international;
    String createdAt;
    String updatedAt;
}
