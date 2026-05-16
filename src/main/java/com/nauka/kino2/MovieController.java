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
    public String index(Model model, @RequestParam(required = false) String szukaj) {
        model.addAttribute("movies", movieService.searchMoviesByTytul(szukaj));
        model.addAttribute("ostatnioSzukane", szukaj);
        return "index";
    }

    @PostMapping("/add")
    public String addMovie(@RequestParam String tytul, @RequestParam String rezyser, @RequestParam int czasTrwania) {
        movieService.addMovieWithDirector(tytul,rezyser,czasTrwania);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie",movie);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable Long id, @RequestParam String tytul, @RequestParam int czasTrwania){
        Movie movie= movieService.getMovieById(id);

        movie.setTytul(tytul);
        movie.setCzasTrwania(czasTrwania);

        movieService.saveMovie(movie);

        return "redirect:/";
    }
}
