package com.devteria.airline_be.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    AIRCRAFT_EXISTED(1012, "Aircraft existed", HttpStatus.BAD_REQUEST),
    AIRCRAFT_NOT_EXISTED(1015, "Aircraft not existed", HttpStatus.NOT_FOUND),
    AIRPORT_EXISTED(1022, "Airport existed", HttpStatus.BAD_REQUEST),
    AIRPORT_NOT_EXISTED(1025, "Airport not existed", HttpStatus.NOT_FOUND),
    ROUTE_EXISTED(1032, "Route existed", HttpStatus.BAD_REQUEST),
    ROUTE_NOT_EXISTED(1035, "Route not existed", HttpStatus.NOT_FOUND),
    FLIGHT_EXISTED(1042, "Flight existed", HttpStatus.BAD_REQUEST),
    FLIGHT_NOT_EXISTED(1045, "Flight not existed", HttpStatus.NOT_FOUND),
    TICKET_EXISTED(1052, "Ticket existed", HttpStatus.BAD_REQUEST),
    TICKET_NOT_EXISTED(1055, "Ticket not existed", HttpStatus.NOT_FOUND),
    BOOKING_EXISTED(1062, "Booking existed", HttpStatus.BAD_REQUEST),
    BOOKING_NOT_EXISTED(1065, "Booking not existed", HttpStatus.NOT_FOUND),
    BAGGAGE_EXISTED(1072, "Booking existed", HttpStatus.BAD_REQUEST),
    BAGGAGE_NOT_EXISTED(1075, "Booking not existed", HttpStatus.NOT_FOUND),
    EMAIL_EXISTED(1082, "Email existed", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_EXISTED(1085, "Email not existed", HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
