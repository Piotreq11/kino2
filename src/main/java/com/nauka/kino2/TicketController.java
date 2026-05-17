package com.nauka.kino2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bilety")
public class TicketController {

    private final TicketService ticketService;
    private final ScreeningService screeningService;

    public TicketController(TicketService ticketService, ScreeningService screeningService) {
        this.ticketService = ticketService;
        this.screeningService = screeningService;
    }

    @GetMapping("/kup/{screeningId}")
    public String showBuyTicketForm(@PathVariable Long screeningId, Model model){
        Screening screening = screeningService.getScreeningById(screeningId);

        model.addAttribute("screening", screening);
        return "buy_ticket";
    }

    @PostMapping("/kup")
    public String buyTicket(@RequestParam String imieNazwisko, @RequestParam int numerMiejsca, @RequestParam Long screeningId){

        Screening screening = screeningService.getScreeningById(screeningId);

        Ticket newTicket = new Ticket(imieNazwisko, numerMiejsca, screening);

        ticketService.buyTicket(newTicket);

        return "redirect:/seanse";

    }
}
