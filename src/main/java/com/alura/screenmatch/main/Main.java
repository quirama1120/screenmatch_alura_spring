package com.alura.screenmatch.main;
import com.alura.screenmatch.logical.ConsultingMovies;
import com.alura.screenmatch.logical.ConsultingSeries;
import com.alura.screenmatch.model.Serie;
import com.alura.screenmatch.model.SeriesData;
import com.alura.screenmatch.repository.SerieRepository;

import java.util.*;

public class Main {
    private final SerieRepository repository;
    public Main(SerieRepository repository) {
        this.repository = repository;
    }

    public void mainCall() {
        Scanner keyword = new Scanner(System.in);
        ConsultingSeries consultingSeries = new ConsultingSeries();
        ConsultingMovies consultingMovies = new ConsultingMovies();
        List<SeriesData> dataList = new ArrayList<>();

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
                    List<Serie> serieList = seriesData.stream()
                                    .map(Serie::new)
                            .toList();
                    repository.saveAll(serieList);
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
