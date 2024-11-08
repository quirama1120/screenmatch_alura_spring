package com.alura.screenmatch.controller;
import com.alura.screenmatch.dto.EpisodeDTO;
import com.alura.screenmatch.dto.SerieDTO;
import com.alura.screenmatch.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {
    @Autowired
    private SeriesService service;

    @GetMapping()
    public List<SerieDTO> gettingSeries () {
        return service.gettingSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> gettingTop5() {
        return service.gettingTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> gettinMostRecentReleases () {
        return service.gettingMostRecentReleases();
    }

    @GetMapping("/{id}")
    public  SerieDTO gettingById(@PathVariable Long id) {
        return service.gettingById(id);
    }

}
