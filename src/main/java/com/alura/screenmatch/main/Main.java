package com.alura.screenmatch.main;
import com.alura.screenmatch.logical.ConsultingEpisodes;
import com.alura.screenmatch.logical.ConsultingMovies;
import com.alura.screenmatch.logical.ConsultingSeries;
import com.alura.screenmatch.model.*;
import com.alura.screenmatch.repository.EpisodeRepository;
import com.alura.screenmatch.repository.SerieRepository;

import java.util.*;

public class Main {
    private final SerieRepository repository;
    private final EpisodeRepository episodeRepository;

    public Main(SerieRepository repository, EpisodeRepository episodeRepository) {
        this.repository = repository;
        this.episodeRepository = episodeRepository;
    }

    public void mainCall() {
        Scanner keyword = new Scanner(System.in);
        ConsultingSeries consultingSeries = new ConsultingSeries();
        ConsultingMovies consultingMovies = new ConsultingMovies();
        List<SeriesData> dataList = new ArrayList<>();
        ConsultingEpisodes consultingEpisodes = new ConsultingEpisodes();

        boolean out = true;
        while	(out) {
            System.out.println("""
					Ingresa la opción que desees consultar
					1. Consultar series.
					2. Consultar películas.
					0. Salir.
					""");
            int userInput = keyword.nextInt();
            switch (userInput) {
                case 1 -> {
                    List<SeriesData> seriesData =  consultingSeries.consultingSeriesExecution();
                    List<EpisodesData> episodesData = consultingEpisodes.consultingEpisodesExecution(seriesData);

                    List<Serie> serieList = seriesData.stream()
                            .map(Serie::new)
                            .filter(serie -> repository.findByTitle(serie.getTitle()).isEmpty())
                            .toList();
                    if(!serieList.isEmpty()){
                        repository.saveAll(serieList);
                        System.out.println("Nuevas series guardadas en la base de datos.");



                    } else {
                        System.out.println("Todas las series ya existen en la base de datos.");
                    }

                    List<Episode> episodeList = episodesData.stream()
                            .map(Episode::new)
                            .filter(episode -> episodeRepository.findByEpisode(episode.getEpisode()).isEmpty())
                            .toList();
                    if(!episodeList.isEmpty()) {
                        episodeRepository.saveAll(episodeList);
                        System.out.println("Nuevos episodios guardados en la base de datos.");

                    } else {
                        System.out.println("Todos los episodios ya existen en la base de datos.");
                    }

                } case 2 -> {
                    List <SeriesData> moviesData = consultingMovies.consultingMoviesExecution();

                    dataList.addAll(moviesData);
                }
                case 0 -> {
                    var series = repository.findAll();
                    System.out.println(series);
                    System.out.println("Saliendo...");
                    out = false;
                }
            }
        }
    }
}
