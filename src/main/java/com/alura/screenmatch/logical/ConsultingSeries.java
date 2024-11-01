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
        return Collections.singletonList(dataSeries);
    }
}


