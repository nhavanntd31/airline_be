package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.BookingRequest;
import com.devteria.airline_be.dto.response.BookingResponse;
import com.devteria.airline_be.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toBooking(BookingRequest bookingRequest);

    @Mapping(source = "seat.aircraft.seats", target = "seat")
    @Mapping(source = "user.name", target = "user")
    @Mapping(source = "user.id", target = "user_id")
    BookingResponse toBookingResponse(Booking booking);

    void updateBooking(@MappingTarget Booking booking, BookingRequest bookingRequest);
}
