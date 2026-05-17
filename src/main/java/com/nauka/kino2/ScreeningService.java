package com.nauka.kino2;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository){
        this.screeningRepository=screeningRepository;
    }

    public List<Screening> getAllScreenings(){
        return screeningRepository.findAll();
    }

    public void addScreening(Screening screening){
        screeningRepository.save(screening);
    }

    public Screening getScreeningById(Long id){
        return screeningRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Nie znaleziono seansu o ID: "+ id));
    }

    public void saveScreening(Screening screening){
        screeningRepository.save(screening);
    }

    public void deleteScreening(Long id){
        screeningRepository.deleteById(id);
    }
}
