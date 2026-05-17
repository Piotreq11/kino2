package com.nauka.kino2;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void buyTicket(Ticket ticket){
        boolean czyZajete = ticketRepository.findByScreeningIdAndNumerMiejsca((ticket.getScreening().getId()), ticket.getNumerMiejsca()).isPresent();
        if(czyZajete)
            throw new IllegalStateException("To miejsce jest już zarezerwowane na ten seans!");

        ticketRepository.save(ticket);
    }
}
