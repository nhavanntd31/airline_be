package com.devteria.airline_be.entity;

import java.time.LocalDateTime;

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
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 36)
    String id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    Flight seat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updatedAt;

}
