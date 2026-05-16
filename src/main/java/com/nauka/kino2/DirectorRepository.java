package com.nauka.kino2;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    Optional<Director> findByImieNazwisko(String imieNazwisko);
}
