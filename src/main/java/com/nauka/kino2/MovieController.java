package com.nauka.kino2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "index";
    }

    @PostMapping("/add")
    public String addMovie(@RequestParam String tytul, @RequestParam String rezyser, @RequestParam int czasTrwania) {
        Movie newMovie = new Movie(tytul, rezyser, czasTrwania);
        movieService.addMovie(newMovie);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return "redirect:/";
    }
}
