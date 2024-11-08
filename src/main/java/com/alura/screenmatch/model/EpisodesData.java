package com.alura.screenmatch.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public record EpisodesData(@JsonAlias("Episode") String episode,
                           @JsonAlias("Title") String title,
                           @JsonAlias("imdbRating") String imdbRating,
                           @JsonAlias("Released") String released,
                           @JsonAlias("Season") String season) {

}
