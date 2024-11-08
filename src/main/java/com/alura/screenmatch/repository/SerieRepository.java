package com.alura.screenmatch.repository;
import com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTitle(String tittle);
        List<Serie> findTop5ByOrderByRatingDesc();
        List<Serie> findByTotalSeasonsLessThanEqualAndRatingGreaterThan(Double rating, Integer total_seasons);
        @Query("SELECT s FROM Serie s JOIN s.episodes e GROUP BY s ORDER BY MAX(e.released) DESC LIMIT 5")
        List<Serie> moreRecentReleases();
}
