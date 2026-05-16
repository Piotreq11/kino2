package com.nauka.kino2;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    public MovieService(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void addMovieWithDirector(String tytul, String imieNazwiskoRezysera, int czasTrwania) {
        Director director = directorRepository.findByImieNazwisko(imieNazwiskoRezysera).orElseGet(() -> directorRepository.save(new Director(imieNazwiskoRezysera)));

        Movie newMovie = new Movie(tytul, director, czasTrwania);
        movieRepository.save(newMovie);
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> searchMoviesByTytul(String fraza) {
        if (fraza == null || fraza.trim().isEmpty())
            return movieRepository.findAll();
        return movieRepository.findByTytulContainingIgnoreCase(fraza);
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nie znaleziono filmu o ID: " + id));
    }

    public void saveMovie(Movie movie){
        movieRepository.save(movie);
    }
}
