package com.sintagma.sintagamaflix.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeries(@JsonAlias("Title") String titleName,
                         @JsonAlias("totalSeasons") Integer seasonsTotal,
                         @JsonAlias("imdbRating") String assessmentRating,
                         @JsonAlias("Genre") String genre) {
}

/*Este record pega os valores de:
titleName - títuloNome
seasonsTotal - numeroTemporadas
assessmentRating - avaliacaoSerie
genre - generoSerie
 */