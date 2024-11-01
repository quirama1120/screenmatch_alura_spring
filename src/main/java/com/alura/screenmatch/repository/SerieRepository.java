package com.alura.screenmatch.repository;

import com.alura.screenmatch.model.Episode;
import com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTitle(String tittle);

}
