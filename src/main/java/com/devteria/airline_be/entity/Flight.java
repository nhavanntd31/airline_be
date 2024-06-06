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
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 36)
    String id;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    @JsonIgnore // Ignore serialization of this field to prevent circular reference
    Route route;

    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false)
    @JsonIgnore // Ignore serialization of this field to prevent circular reference
    Aircraft aircraft;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime startTime;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime endTime;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "flights")
    @JsonIgnore // Ignore serialization of this field to prevent circular reference
    Set<Ticket> flightTickets;

}
