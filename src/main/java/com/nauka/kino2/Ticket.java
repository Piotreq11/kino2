package com.nauka.kino2;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imieNazwiskoKlienta;

    private int numerMiejsca;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    public Ticket() {
    }

    public Ticket(String imieNazwiskoKlienta, int numerMiejsca, Screening screening) {
        this.imieNazwiskoKlienta = imieNazwiskoKlienta;
        this.numerMiejsca = numerMiejsca;
        this.screening = screening;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImieNazwiskoKlienta() {
        return imieNazwiskoKlienta;
    }

    public void setImieNazwiskoKlienta(String imieNazwiskoKlienta) {
        this.imieNazwiskoKlienta = imieNazwiskoKlienta;
    }

    public int getNumerMiejsca() {
        return numerMiejsca;
    }

    public void setNumerMiejsca(int numerMiejsca) {
        this.numerMiejsca = numerMiejsca;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }
}
