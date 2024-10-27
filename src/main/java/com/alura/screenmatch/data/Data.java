package com.alura.screenmatch.data;

import com.alura.screenmatch.model.SeriesData;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public List<SeriesData> DataList (SeriesData dataSeries) {
        List<SeriesData> dataSeriesList = new ArrayList<>();
        dataSeriesList.add(dataSeries);
        return dataSeriesList;
    }
}
