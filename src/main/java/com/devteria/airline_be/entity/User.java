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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String firstname;

    @Column(nullable = false)
    String lastname;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column(columnDefinition = "TEXT")
    String avatarUrl;


    @ManyToMany
    Set<Role> roles;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Status status;

    String accessToken;
    String refreshToken;

    @Column(nullable = false)
    Boolean isVerified;

    String otpCode;
    LocalDateTime otpCreatedAt;

    String resetCode;
    LocalDateTime resetCodeCreatedAt;
    LocalDateTime lastLogin;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user")
    Set<Baggage> userBaggages;

    @OneToMany(mappedBy = "user")
    Set<Payment> userPayments;

    @OneToMany(mappedBy = "user")
    Set<Booking> userBookings;

    @Builder.Default
    @Column(nullable = false)
    Boolean isDeleted = Boolean.FALSE;


    public enum Status {
        DRAFT,
        ACTIVE,
        DEACTIVE
    }
}
