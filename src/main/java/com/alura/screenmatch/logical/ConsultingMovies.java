package com.alura.screenmatch.logical;
import com.alura.screenmatch.data.Data;
import com.alura.screenmatch.model.SeriesData;
import com.alura.screenmatch.service.ApiCall;
import com.alura.screenmatch.service.DataConversor;

import java.util.List;
import java.util.Scanner;

public class ConsultingMovies {
    Scanner keyword = new Scanner(System.in);
    ApiCall apiCall = new ApiCall();
    Data data = new Data();
    public List<SeriesData> consultingMoviesExecution() {
        System.out.println("Ingresa el nombre de la película que quieres ver");
        String input = keyword.nextLine();
        input = input.replaceAll(" ", "+");
        String url = "http://www.omdbapi.com/?apikey=6846f2b8&t=" + input;
        String apiCallResponse = apiCall.receivingData(url);
        DataConversor converse = new DataConversor();
        var dataSeries = converse.obtainingData(apiCallResponse, SeriesData.class);
        System.out.println(dataSeries);
        return data.DataList(dataSeries);
    }
}
