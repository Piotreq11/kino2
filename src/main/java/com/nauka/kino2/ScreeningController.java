package com.nauka.kino2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/seanse")
public class ScreeningController {

    private final ScreeningService screeningService;
    private final MovieService movieService;

    public ScreeningController(ScreeningService screeningService, MovieService movieService) {
        this.screeningService = screeningService;
        this.movieService = movieService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("screenings", screeningService.getAllScreenings());
        model.addAttribute("movies", movieService.getAllMovies());
        return "screenings";
    }

    @PostMapping("/add")
    public String addScreening(@RequestParam int numerSali, @RequestParam String dataIGodzinaStr, @RequestParam Long movieId){
        Movie movie = movieService.getMovieById(movieId);

        LocalDateTime dataiGodzina = LocalDateTime.parse(dataIGodzinaStr);

        Screening screening = new Screening(numerSali,dataiGodzina, movie);
        screeningService.addScreening(screening);

        return "redirect:/seanse";
    }
}
