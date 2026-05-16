package com.nauka.kino2;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directors")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imieNazwisko;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    private List<Movie> filmy = new ArrayList<>();

    public Director(){}

    public Director(String imieNazwisko){
        this.imieNazwisko=imieNazwisko;
    }

    public String getImieNazwisko() {
        return imieNazwisko;
    }

    public void setImieNazwisko(String imieNazwisko) {
        this.imieNazwisko = imieNazwisko;
    }

    public List<Movie> getFilmy() {
        return filmy;
    }

    public void setFilmy(List<Movie> filmy) {
        this.filmy = filmy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
