package com.devteria.airline_be.controller;

import com.devteria.airline_be.dto.request.BookingRequest;
import com.devteria.airline_be.dto.request.ApiResponse;
import com.devteria.airline_be.dto.response.BookingResponse;
import com.devteria.airline_be.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ApiResponse<List<BookingResponse>> getAllBookings() {
        List<BookingResponse> bookings = bookingService.getAllBookings();
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookings)
                .message("Get all success!")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<BookingResponse> getBookingById(@PathVariable String id) {
        BookingResponse booking = bookingService.getBookingById(id);
        return ApiResponse.<BookingResponse>builder()
                .result(booking)
                .message("Get success!")
                .build();
    }

    @PostMapping
    public ApiResponse<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest) {
        BookingResponse booking = bookingService.createBooking(bookingRequest);
        return ApiResponse.<BookingResponse>builder()
                .result(booking)
                .message("Create succesfully!")
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<BookingResponse> updateBooking(@PathVariable String id, @RequestBody BookingRequest bookingRequest) {
        BookingResponse booking = bookingService.updateBooking(id, bookingRequest);
        return ApiResponse.<BookingResponse>builder()
                .result(booking)
                .message("Update succesfully!")
                .build();
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return "Delete succesfully!";
    }
}
