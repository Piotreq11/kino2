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
}
