package com.alura.screenmatch.model;


import com.alura.screenmatch.service.ChatGptCalling;

import java.util.Optional;
import java.util.OptionalDouble;

public class Serie {
    private String title;
    private Double rating;
    private Integer totalSeasons;
    private CategoryEnum genre;
    private String actors;
    private String poster;
    private String plot;

    public Serie(SeriesData seriesData) {
        this.title = seriesData.title();
        this.rating = OptionalDouble.of(Double.parseDouble(seriesData.rating())).orElse(0);
        this.totalSeasons = Integer.valueOf(seriesData.totalSeasons());
        this.genre = CategoryEnum.fromString(seriesData.genre().split(",")[0].trim());
        this.actors = seriesData.actors();
        this.poster = seriesData.poster();
//        this.plot = ChatGptCalling.translating(seriesData.plot());
        this.plot = seriesData.plot();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(Integer totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public CategoryEnum getGenre() {
        return genre;
    }

    public void setGenre(CategoryEnum genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return
                " Genre=" + genre +
                ",title='" + title + '\'' +
                ", rating=" + rating +
                ", totalSeasons=" + totalSeasons +
                ", actors='" + actors + '\'' +
                ", poster='" + poster + '\'' +
                ", plot='" + plot + '\'';

    }
}
