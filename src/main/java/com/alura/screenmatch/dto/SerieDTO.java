package com.alura.screenmatch.dto;
import com.alura.screenmatch.model.CategoryEnum;

public record SerieDTO(
                       Long id,
                       String title,
                       Double rating,
                       Integer totalSeasons,
                       CategoryEnum genre,
                       String actors,
                       String poster,
                       String plot) {
}
