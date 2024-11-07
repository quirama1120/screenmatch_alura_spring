package com.alura.screenmatch.controller;
import com.alura.screenmatch.dto.SerieDTO;
import com.alura.screenmatch.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SeriesController {
    @Autowired
    private SeriesService service;

    @GetMapping("/series")
    public List<SerieDTO> gettingSeries () {
        return service.gettingSeries();
    }
    @GetMapping("/series/top5")
    public List<SerieDTO> gettingTop5() {
        return service.gettingTop5();
    }

}
