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

    public List<TicketResponse> createTicketsForFlight(Flight fli) {
        Flight flight = flightRepository.findById(fli.getId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));

        List<Ticket> tickets = IntStream.range(0, 50)
                .mapToObj(i -> {
                    Ticket.Type type = i < 20 ? Ticket.Type.CLASSIC : Ticket.Type.BUSINESS;
                    int price = type == Ticket.Type.CLASSIC ? 100 : 200;
                    return Ticket.builder()
                            .flights(flight)
                            .type(type)
                            .price(price)
                            .createdAt(LocalDateTime.now())
                            .updateAt(LocalDateTime.now())
                            .build();
                })
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
