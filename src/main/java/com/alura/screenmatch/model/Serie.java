package com.alura.screenmatch.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String title;
    private Double rating;
    private Integer totalSeasons;
    @Enumerated(EnumType.STRING)
    private CategoryEnum genre;
    private String actors;
    private String poster;
    private String plot;
    @OneToMany(mappedBy = "serie", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Episode> episodes = new ArrayList<>();

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
    Serie(){}

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        episodes.forEach(e -> e.setSerie(this));
        this.episodes = episodes;
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
