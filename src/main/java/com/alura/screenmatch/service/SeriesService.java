package com.alura.screenmatch.service;
import com.alura.screenmatch.dto.SerieDTO;
import com.alura.screenmatch.model.Serie;
import com.alura.screenmatch.repository.EpisodeRepository;
import com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeriesService {
    @Autowired
    private SerieRepository repository;
    private EpisodeRepository episodeRepository;
    @GetMapping("/series")
    public List<SerieDTO> gettingSeries(){
        return converseData(repository.findAll());
    }
    @GetMapping("/series/top5")
    public List<SerieDTO> gettingTop5() {
        return converseData(repository.findTop5ByOrderByRatingDesc());
    }
    public List<SerieDTO> gettingMostRecentReleases() {
        return converseData(repository.moreRecentReleases());
    }

    public List<SerieDTO> converseData(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitle(), s.getRating(), s.getTotalSeasons(),
                        s.getGenre(), s.getActors(), s.getPoster(), s.getPlot()
                ))
                .collect(Collectors.toList());
    }

    public SerieDTO gettingById(Long id) {
        Optional <Serie> series = repository.findById(id);
        System.out.println(series);
        if(series.isPresent()) {
            Serie s = series.get();
            return new SerieDTO(s.getId(), s.getTitle(), s.getRating(), s.getTotalSeasons(),
                    s.getGenre(), s.getActors(), s.getPoster(), s.getPlot());
        } else {
            return null;
        }
    }
}
