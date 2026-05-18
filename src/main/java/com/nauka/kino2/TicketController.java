package com.nauka.kino2;

import org.springframework.security.core.Authentication;
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
    public String buyTicket(@RequestParam String imieNazwisko, @RequestParam int numerMiejsca, @RequestParam Long screeningId, Model model,@RequestParam String typBiletu, Authentication authentication){

        Screening screening = screeningService.getScreeningById(screeningId);
        boolean czyZalogowany = false;

        if(authentication !=null && authentication.isAuthenticated())
            czyZalogowany=true;

        try {
            double cenaKoncowa=screening.getCenaBiletu();

            if("ULGOWY".equals(typBiletu))
                cenaKoncowa*=0.8d;
            if(czyZalogowany)
                cenaKoncowa*=0.95d;
            Ticket newTicket = new Ticket(authentication.getName(), numerMiejsca, screening, typBiletu ,cenaKoncowa);
            ticketService.buyTicket(newTicket); // Tutaj może wyskoczyć błąd
            return "redirect:/repertuar";

        } catch (IllegalStateException e) {
            // Jeśli złapiemy błąd z serwisu, wracamy do formularza i przekazujemy komunikat
            model.addAttribute("error", e.getMessage());
            model.addAttribute("screening", screening); // Musimy ponownie przekazać dane seansu
            return "buy_ticket";
        }

    }
}
