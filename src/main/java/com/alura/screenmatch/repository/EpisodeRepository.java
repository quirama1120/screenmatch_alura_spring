package com.alura.screenmatch.repository;
import com.alura.screenmatch.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findByTitle(String episode);
}
