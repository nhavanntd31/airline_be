package com.devteria.airline_be.service;

import com.devteria.airline_be.dto.request.BookingRequest;
import com.devteria.airline_be.dto.response.BookingResponse;
import com.devteria.airline_be.entity.Booking;
import com.devteria.airline_be.entity.Flight;
import com.devteria.airline_be.entity.User;
import com.devteria.airline_be.exception.AppException;
import com.devteria.airline_be.exception.ErrorCode;
import com.devteria.airline_be.mapper.BookingMapper;
import com.devteria.airline_be.repository.BookingRepository;
import com.devteria.airline_be.repository.FlightRepository;
import com.devteria.airline_be.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {

    BookingRepository bookingRepository;
    FlightRepository flightRepository;
    UserRepository userRepository;
    BookingMapper bookingMapper;

    public List<BookingResponse> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(bookingMapper::toBookingResponse)
                .collect(Collectors.toList());
    }

    public BookingResponse getBookingById(String id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toBookingResponse)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_EXISTED));
    }

    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Flight flight = flightRepository.findById(bookingRequest.getSeat().getId())
                .orElseThrow(() -> new AppException(ErrorCode.FLIGHT_NOT_EXISTED));
        User user = userRepository.findById(bookingRequest.getUser().getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Booking booking = bookingMapper.toBooking(bookingRequest);
        booking.setSeat(flight);
        booking.setUser(user);
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toBookingResponse(savedBooking);
    }

    public BookingResponse updateBooking(String id, BookingRequest bookingRequest) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_EXISTED));
        bookingMapper.updateBooking(existingBooking, bookingRequest);
        existingBooking.setUpdatedAt(LocalDateTime.now());
        Booking savedBooking = bookingRepository.save(existingBooking);
        return bookingMapper.toBookingResponse(savedBooking);
    }

    public void deleteBooking(String id) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));
        bookingRepository.delete(existingBooking);
    }
}
