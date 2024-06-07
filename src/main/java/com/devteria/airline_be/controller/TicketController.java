package com.devteria.airline_be.controller;

import com.devteria.airline_be.dto.request.TicketRequest;
import com.devteria.airline_be.dto.request.ApiResponse;
import com.devteria.airline_be.dto.response.TicketResponse;
import com.devteria.airline_be.service.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TicketController {

    TicketService ticketService;

    @GetMapping
    public ApiResponse<List<TicketResponse>> getAllTickets() {
        List<TicketResponse> tickets = ticketService.getAllTickets();
        return ApiResponse.<List<TicketResponse>>builder()
                .result(tickets)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<TicketResponse> getTicketById(@PathVariable String id) {
        TicketResponse ticket = ticketService.getTicketById(id);
        return ApiResponse.<TicketResponse>builder()
                .result(ticket)
                .build();
    }

    @PostMapping
    public ApiResponse<List<TicketResponse>> createTicket(@RequestBody TicketRequest ticketRequest) {
        List<TicketResponse> createdTicket = ticketService.createTicketsForFlight(ticketRequest);
        return ApiResponse.<List<TicketResponse>>builder()
                .result(createdTicket)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<TicketResponse> updateTicket(@PathVariable String id, @RequestBody TicketRequest ticketRequest) {
        TicketResponse updatedTicket = ticketService.updateTicket(id, ticketRequest);
        return ApiResponse.<TicketResponse>builder()
                .result(updatedTicket)
                .build();
    }

    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
        return "Delete ticket successfully!";
    }
}
