package com.alura.screenmatch.logical;

import com.alura.screenmatch.model.EpisodesData;
import com.alura.screenmatch.model.SeasonData;
import com.alura.screenmatch.model.SeriesData;
import com.alura.screenmatch.service.ApiCall;
import com.alura.screenmatch.service.DataConversor;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ConsultingEpisodes {
    Scanner keyword = new Scanner(System.in);
    final String URL_IMDB = "http://www.omdbapi.com/?apikey=6846f2b8&t=";
    ApiCall apiCall = new ApiCall();
    DataConversor converse = new DataConversor();
    public List<EpisodesData> consultingEpisodesExecution (List<SeriesData> serieList) {
        var seasonInformation = serieList.stream()
                .map(SeriesData::totalSeasons)
                        .findFirst()
                                .orElse("N/A");
        System.out.println("Basado en el total de temporadas: " +
                "(" + seasonInformation + ") escoge la temporada que quieras ver");
        String inputSeason = keyword.nextLine();
        String serieTitle = serieList.stream()
                .map(SeriesData::title)
                .findFirst()
                .orElse("");
        String seriesTitleTrunc = serieTitle.replaceAll(" ", "+");
        String urlSeason = URL_IMDB + seriesTitleTrunc + "&Season=" + inputSeason;
        String apicalSeasonResponse = apiCall.receivingData(urlSeason);

        var dataSeasonSeries = converse.obtainingData(apicalSeasonResponse, SeasonData.class);
        System.out.println("Los episodios de la temporada " + inputSeason + " de la serie " + serieList.stream()
                .map(SeriesData::title)+ " son: ");
        var episodesData = dataSeasonSeries.episodes();
        System.out.println(episodesData);
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
        return episodesData;
    }
}
