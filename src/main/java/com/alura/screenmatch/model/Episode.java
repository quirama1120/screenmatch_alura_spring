package com.alura.screenmatch.model;
import jakarta.persistence.*;

import java.util.OptionalDouble;
@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String episode;
    private String title;
    private String imdbRating;
    private String released;
    @ManyToOne
    private Serie serie;

    public Episode() {}
    public Episode(Integer number, EpisodesData data) {
        this.title = data.title();
        this.imdbRating = String.valueOf(OptionalDouble.of(Double.parseDouble(data.imdbRating())).orElse(0));
        this.episode = data.episode();
        this.released = data.released();
    }

    public Episode(EpisodesData data) {
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return
                "episode='" + episode + '\'' +
                ", title='" + title + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", released='" + released + '\'' +
                ", serie=" + serie;
    }
}
