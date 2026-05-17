package com.nauka.kino2;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void buyTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }
}
