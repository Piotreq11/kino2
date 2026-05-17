package com.nauka.kino2;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tytul;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    private int czasTrwania;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Screening> seanse = new ArrayList<>();

    public Movie() {
    }

    public Movie(String tytul, Director director, int czasTrwania) {
        this.tytul = tytul;
        this.director=director;
        this.czasTrwania = czasTrwania;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }
}
