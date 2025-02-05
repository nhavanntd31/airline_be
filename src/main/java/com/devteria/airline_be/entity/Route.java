package com.devteria.airline_be.entity;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 36)
    String id;

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    @JsonIgnore // Ignore serialization of this field to prevent circular reference
    Airport origin;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    @JsonIgnore // Ignore serialization of this field to prevent circular reference
    Airport destination;

    // @ManyToOne
    // @JoinColumn(name = "aircraft_id", nullable = false)
    // Aircraft aircraft;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updatedAt;

    @Column(nullable = false)
    Boolean isRoundTrip;

    @OneToMany(mappedBy = "route")
    @JsonIgnore // Ignore serialization of this field to prevent circular reference
    Set<Flight> flights;
}
