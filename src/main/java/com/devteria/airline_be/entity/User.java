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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column(columnDefinition = "TEXT")
    String avatarUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Role role;

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

    @Column(nullable = false)
    Boolean isDeleted;

    public enum Role {
        ADMIN,
        USER
    }

    public enum Status {
        DRAFT,
        ACTIVE,
        DEACTIVE
    }
}
