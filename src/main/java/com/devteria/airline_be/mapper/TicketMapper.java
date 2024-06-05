package com.devteria.airline_be.mapper;

import com.devteria.airline_be.dto.request.TicketRequest;
import com.devteria.airline_be.dto.response.TicketResponse;
import com.devteria.airline_be.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    Ticket toTicket(TicketRequest request);

    TicketResponse toTicketResponse(Ticket ticket);

    void updateTicket(@MappingTarget Ticket ticket, TicketRequest request);
}
