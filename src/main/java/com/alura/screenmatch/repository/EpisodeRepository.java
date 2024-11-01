package com.alura.screenmatch.repository;

import com.alura.screenmatch.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    Optional<Episode> findByEpisode(String episode);
}
