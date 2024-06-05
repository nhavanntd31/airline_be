package com.devteria.airline_be.entity;

import java.time.LocalDateTime;
import java.util.Set;

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
@Table(name = "airports")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 36)
    String id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    Boolean international;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "origin")
    Set<Route> originRoutes;

    @OneToMany(mappedBy = "destination")
    Set<Route> destinationRoutes;
}
