package com.nauka.kino2;

import org.springframework.cglib.core.Local;
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

    /*
    strefa klienta
     */

    @GetMapping("/repertuar")
    public String ShowRepertuar(Model model) {
        model.addAttribute("screenings", screeningService.getAllScreenings());
        return "repertuar";
    }

    /*
    Strefa Managera
     */

    //strona glowna panelu admina
    @GetMapping("/adnub/seanse")
    public String adminIndex(Model model) {
        model.addAttribute("screenings", screeningService.getAllScreenings());
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin_screenings";
    }

    //dodawanie seansu przez admina

    @PostMapping("/admin/seanse/add")
    public String addScreening(@RequestParam int numerSali, @RequestParam String dataGodzinaStr, @RequestParam Long movieId) {
        Movie movie = movieService.getMovieById(movieId);
        LocalDateTime dataIGodzina = LocalDateTime.parse(dataGodzinaStr);
        Screening screening = new Screening(numerSali, dataIGodzina, movie);
        screeningService.addScreening(screening);
        return "redirect:/admin/seanse";
    }

    //usuwanie seansu przez admina

    @PostMapping("/admin/seanse/delete/{id}")
    public String deleteScreening(@PathVariable Long id) {
        screeningService.deleteScreening(id);
        return "redirect:/admin/seanse";
    }

    // edycja seansu przez admina

    @GetMapping("/admin/seanse/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Screening screening = screeningService.getScreeningById(id);
        model.addAttribute("screening", screening);
        model.addAttribute("movies", movieService.getAllMovies());
        return "edit_screening";
    }

    //zapisanie zmian po edycji

    @PostMapping("/admin/seanse/edit/{id}")
    public String updateScreening(@PathVariable Long id, @RequestParam int numerSali, @RequestParam String dataGodzinaStr, @RequestParam Long movieId) {
        Screening screening = screeningService.getScreeningById(id);
        Movie movie = movieService.getMovieById(movieId);

        screening.setNumerSali(numerSali);
        screening.setDataIGodzina(LocalDateTime.parse(dataGodzinaStr));
        screening.setMovie(movie);

        screeningService.saveScreening(screening);
        return "redirect:/admin/seanse";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("screenings", screeningService.getAllScreenings());
        model.addAttribute("movies", movieService.getAllMovies());
        return "screenings";
    }

}
