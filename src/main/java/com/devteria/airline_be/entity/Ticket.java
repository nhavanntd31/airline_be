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
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, length = 36)
    String id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    Flight flights;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Ticket.Type type;

    @Column(nullable = false)
    int price;

    @Column(nullable = false)
    String description;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updateAt;

    public enum Type {
        CLASSIC,
        BUSINESS
    }

}
