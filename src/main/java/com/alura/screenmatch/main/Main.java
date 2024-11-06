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
					3. Series en la base de datos con un rating y un número de temporadas específico.
					0. Salir.
					""");
            int userInput = keyword.nextInt();
            switch (userInput) {
                case 1 -> {
                    List<SeriesData> seriesData = consultingSeries.consultingSeriesExecution();

                    List<Serie> serieList = seriesData.stream()
                            .map(Serie::new)
                            .filter(serie -> repository.findByTitle(serie.getTitle()).isEmpty())
                            .toList();

                    if (!serieList.isEmpty()) {
                        repository.saveAll(serieList);
                        System.out.println("Nueva serie guardada en la base de datos para ser persistida.");
                        List<EpisodesData> episodesData = consultingEpisodes.consultingEpisodesExecution(seriesData);
                        Serie serie = serieList.get(0);
                        List<Episode> episodeList = episodesData.stream()
                                .map(Episode::new)
                                .peek(episode -> episode.setSerie(serie))
                                .filter(episode -> episodeRepository.findByTitle(episode.getTitle()).isEmpty())
                                .toList();
                        episodeRepository.saveAll(episodeList);
                    } else {
                        Optional<Serie> optionalSerie = repository.findByTitle(seriesData.get(0).title());

                        if (optionalSerie.isPresent()) {
                            Serie existingSerie = optionalSerie.get();

                            List<EpisodesData> episodesData = consultingEpisodes.consultingEpisodesExecution(seriesData);

                            List<Episode> newEpisodesToRepository = episodesData.stream()
                                    .map(Episode::new)
                                    .filter(episode -> episodeRepository.findByTitle(episode.getTitle()).isEmpty())
                                    .peek(episode -> episode.setSerie(existingSerie))
                                    .toList();

                            if (!newEpisodesToRepository.isEmpty()) {
                                episodeRepository.saveAll(newEpisodesToRepository);
                                System.out.println("Nuevos episodios guardados en la base de datos de la serie persistida.");
                            } else {
                                System.out.println("Todos los episodios ya existen en la base de datos de la serie persistida.");
                            }
                        }
                    }
                } case 2 -> {
                    List <SeriesData> moviesData = consultingMovies.consultingMoviesExecution();

                    dataList.addAll(moviesData);
                } case 3 -> {
                    System.out.println("Ingresa el número de temporadas de la serie que quieres filtrar");
                    Integer inputUserRating = keyword.nextInt();
                    keyword.nextLine();
                    System.out.println("Ingresa el rating de la serie que quieres filtrar");
                    Double inputUserTotalSeasons = keyword.nextDouble();
                    List <Serie> serieList = repository.
                            findByTotalSeasonsLessThanEqualAndRatingGreaterThan(inputUserTotalSeasons, inputUserRating);
                    System.out.println("**********");
                    System.out.println("Las series que complen tus condiciones son: ");
                    serieList.forEach(System.out::println);
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
