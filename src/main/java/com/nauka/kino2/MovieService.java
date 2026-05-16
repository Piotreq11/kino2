package com.nauka.kino2;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public void addMovie(Movie movie){
        movieRepository.save(movie);
    }

    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

    public List<Movie> searchMoviesByTytul(String fraza){
        if(fraza==null || fraza.trim().isEmpty())
            return movieRepository.findAll();
        return movieRepository.findByTytulContainingIgnoreCase(fraza);
    }
}
