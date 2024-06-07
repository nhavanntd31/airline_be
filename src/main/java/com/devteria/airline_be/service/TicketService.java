package com.devteria.airline_be.service;

import com.devteria.airline_be.dto.request.TicketRequest;
import com.devteria.airline_be.dto.response.TicketResponse;
import com.devteria.airline_be.entity.Flight;
import com.devteria.airline_be.entity.Ticket;
import com.devteria.airline_be.exception.AppException;
import com.devteria.airline_be.exception.ErrorCode;
import com.devteria.airline_be.mapper.TicketMapper;
import com.devteria.airline_be.repository.FlightRepository;
import com.devteria.airline_be.repository.TicketRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketService {
    TicketRepository ticketRepository;

    FlightRepository flightRepository;

    TicketMapper ticketMapper;

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toTicketResponse)
                .collect(Collectors.toList());
    }

    public TicketResponse getTicketById(String id) {
        return ticketMapper.toTicketResponse(ticketRepository.findById(id).orElseThrow(
                ()-> new AppException(ErrorCode.TICKET_NOT_EXISTED)));
    }

    public List<TicketResponse> createTicketsForFlight(TicketRequest ticketRequest) {
        Flight flight = flightRepository.findById(ticketRequest.getFlight().getId())
                .orElseThrow(() -> new AppException(ErrorCode.FLIGHT_NOT_EXISTED));

        int seats = flight.getAircraft().getSeats();
        int a = (seats + 20) / 2;
        int b = a - 20;
        if(ticketRequest.getType().name() == Ticket.Type.CLASSIC.name()){
            seats = a;
        } else seats = b;


        List<Ticket> tickets = IntStream.range(0, seats)
                .mapToObj( i -> Ticket.builder()
                            .flights(flight)
                            .type(ticketRequest.getType())
                            .price(ticketRequest.getPrice())
                            .createdAt(LocalDateTime.now())
                            .updateAt(LocalDateTime.now())
                            .build())
                .collect(Collectors.toList());

        ticketRepository.saveAll(tickets);

        return tickets.stream()
                .map(ticketMapper::toTicketResponse)
                .collect(Collectors.toList());
    }


    public TicketResponse updateTicket(String id, TicketRequest ticketRequest) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.TICKET_NOT_EXISTED));

        ticketMapper.updateTicket(ticket, ticketRequest);
        ticket.setFlights(ticket.getFlights());
        ticket.setUpdateAt(LocalDateTime.now());

        return ticketMapper.toTicketResponse(ticketRepository.save(ticket));
    }

    public void deleteTicket(String id) {
        if (!ticketRepository.existsById(id)) {
            throw new AppException(ErrorCode.TICKET_NOT_EXISTED);
        }
        ticketRepository.deleteById(id);
    }
}
