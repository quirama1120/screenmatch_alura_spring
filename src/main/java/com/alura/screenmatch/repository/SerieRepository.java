package com.alura.screenmatch.repository;

import com.alura.screenmatch.model.Episode;
import com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTitle(String tittle);
        List<Serie> findByTotalSeasonsLessThanEqualAndRatingGreaterThan(Double rating, Integer total_seasons);

}
