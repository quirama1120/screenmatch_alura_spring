package com.alura.screenmatch.main;
import com.alura.screenmatch.logical.ConsultingMovies;
import com.alura.screenmatch.logical.ConsultingSeries;
import com.alura.screenmatch.model.Serie;
import com.alura.screenmatch.model.SeriesData;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
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
                    dataList.addAll(seriesData);
                } case 2 -> {
                    List <SeriesData> moviesData = consultingMovies.consultingMoviesExecution();
                    dataList.addAll(moviesData);
                }
                case 0 -> {
                    List<Serie> series = new ArrayList<>();
                    series = dataList.stream()
                            .map(Serie::new)
                            .sorted(Comparator.comparing(Serie::getGenre))
                            .toList();



                    System.out.println("Tus series y/o películas consultadas fueron: " + series);
                    System.out.println("Saliendo...");
                    out = false;
                }
            }
        }
    }
}
