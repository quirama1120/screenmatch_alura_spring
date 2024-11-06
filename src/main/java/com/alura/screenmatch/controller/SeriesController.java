package com.alura.screenmatch.controller;
import com.alura.screenmatch.dto.SerieDTO;
import com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SeriesController {
    @Autowired
    private SerieRepository repository;
    @GetMapping("/series")
    public List<SerieDTO> gettingSeries(){
        return repository.findAll().stream()
                .map(s -> new SerieDTO(s.getTitle(), s.getRating(), s.getTotalSeasons(),
                        s.getGenre(), s.getActors(), s.getPoster(), s.getPlot()
                        ))
                .collect(Collectors.toList());

        }
}
