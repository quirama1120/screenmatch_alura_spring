package com.alura.screenmatch.logical;
import com.alura.screenmatch.model.EpisodesData;
import com.alura.screenmatch.model.SeasonData;
import com.alura.screenmatch.model.SeriesData;
import com.alura.screenmatch.service.ApiCall;
import com.alura.screenmatch.service.DataConversor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class ConsultingSeries  {
    Scanner keyword = new Scanner(System.in);
    ApiCall apiCall = new ApiCall();

    public List<SeriesData> consultingSeriesExecution() {
        final String URL_IMDB = "http://www.omdbapi.com/?apikey=6846f2b8&t=";

        System.out.println("Ingresa el nombre de la serie o película que quieres ver");
        String input = keyword.nextLine();
        String inputUrl = input.replaceAll(" ", "+");
        String url = URL_IMDB + inputUrl;

        String apiCallResponse = apiCall.receivingData(url);
        DataConversor converse = new DataConversor();
        var dataSeries = converse.obtainingData(apiCallResponse, SeriesData.class);
        System.out.println(dataSeries);
        System.out.println("Basado en el total de temporadas: " +
                "(" + dataSeries.totalSeasons() + ")" + " escoge la temporada que quieras ver");

        String inputSeason = keyword.nextLine();
        String urlSeason = URL_IMDB + inputUrl + "&Season=" + inputSeason;
        String apicalSeasonResponse = apiCall.receivingData(urlSeason);

        var dataSeasonSeries = converse.obtainingData(apicalSeasonResponse, SeasonData.class);
        System.out.println("Los episodios de la temporada " + inputSeason + " de la serie " + input + " son: ");

        dataSeasonSeries.episodes().forEach(t -> {
            System.out.println("Episodio " + t.episode() + ":");
            System.out.println(t.title());
            System.out.println("**************************");
        });

        System.out.println("El top 3 de la temporada es: ");
        dataSeasonSeries.episodes().stream()
                .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodesData::imdbRating).reversed())
                .limit(3)
                .forEach(e -> {
                    System.out.println("Episodio: " + e.episode());
                    System.out.println(e.title() + " con una puntuación de: " + e.imdbRating());
                });
        return Collections.singletonList(dataSeries);
    }
}


