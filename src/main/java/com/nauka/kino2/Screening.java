package com.nauka.kino2;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "screenings")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numerSali;

    private LocalDateTime dataIGodzina;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
    private List<Ticket> bilety = new ArrayList<>();

    public Screening() {
    }

    public Screening(int numerSali, LocalDateTime dataIGodzina, Movie movie) {
        this.numerSali = numerSali;
        this.dataIGodzina = dataIGodzina;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumerSali() {
        return numerSali;
    }

    public void setNumerSali(int numerSali) {
        this.numerSali = numerSali;
    }

    public LocalDateTime getDataIGodzina() {
        return dataIGodzina;
    }

    public void setDataIGodzina(LocalDateTime dataIGodzina) {
        this.dataIGodzina = dataIGodzina;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Ticket> getBilety(){
        return bilety;
    }

    public void setBilety(List<Ticket> bilety){
        this.bilety=bilety;
    }

    public int getZajeteMiejsca(){
        return this.bilety.size();
    }

    public int getWolneMiejsca(){
        int pojemnoscSali=3;
        return pojemnoscSali-getZajeteMiejsca();
    }
}
