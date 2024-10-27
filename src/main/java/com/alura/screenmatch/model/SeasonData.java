package com.alura.screenmatch.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(
        @JsonAlias("Season") String season,
        @JsonAlias("Title") String title,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Episodes") List<EpisodesData> episodes){
}
