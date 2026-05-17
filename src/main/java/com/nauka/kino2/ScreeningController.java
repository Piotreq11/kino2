package com.nauka.kino2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String index(Model model) {
        model.addAttribute("screenings", screeningService.getAllScreenings());
        model.addAttribute("movies", movieService.getAllMovies());
        return "screenings";
    }

    @PostMapping("/add")
    public String addScreening(@RequestParam int numerSali, @RequestParam String dataIGodzinaStr, @RequestParam Long movieId) {
        Movie movie = movieService.getMovieById(movieId);

        LocalDateTime dataiGodzina = LocalDateTime.parse(dataIGodzinaStr);

        Screening screening = new Screening(numerSali, dataiGodzina, movie);
        screeningService.addScreening(screening);

        return "redirect:/seanse";
    }

    @PostMapping("/delete/{id}")
    public String deleteScreening(@PathVariable Long id) {
        screeningService.deleteScreening(id);
        return "redirect:/seanse";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Screening screening = screeningService.getScreeningById(id);
        model.addAttribute("screening", screening);
        model.addAttribute("movies", movieService.getAllMovies());
        return "edit_screening";
    }

    @PostMapping("/edit/{id}")
    public String updateScreening(@PathVariable Long id, @RequestParam int numerSali, @RequestParam String dataGodzinaStr, @RequestParam Long movieId){
        Screening screening = screeningService.getScreeningById(id);
        Movie movie = movieService.getMovieById(movieId);

        screening.setNumerSali(numerSali);
        screening.setDataIGodzina(LocalDateTime.parse(dataGodzinaStr));
        screening.setMovie(movie);

        screeningService.saveScreening(screening);

        return "redirect:/seanse";

    }

}
