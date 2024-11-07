package com.alura.screenmatch.service;
import com.alura.screenmatch.dto.SerieDTO;
import com.alura.screenmatch.model.Serie;
import com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesService {
    @Autowired
    private SerieRepository repository;
    @GetMapping("/series")
    public List<SerieDTO> gettingSeries(){
        return converseData(repository.findAll());
    }
    @GetMapping("/series/top5")
    public List<SerieDTO> gettingTop5() {
        return converseData(repository.findTop5ByOrderByRatingDesc());
    }

    public List<SerieDTO> converseData(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getTitle(), s.getRating(), s.getTotalSeasons(),
                        s.getGenre(), s.getActors(), s.getPoster(), s.getPlot()
                ))
                .collect(Collectors.toList());
    }
}
