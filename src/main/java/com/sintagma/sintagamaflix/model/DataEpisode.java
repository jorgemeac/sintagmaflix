package com.sintagma.sintagamaflix.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisode(@JsonAlias("Title") String titleNameEpisode,
                          @JsonAlias("Episode") Integer numberEpisode,
                          @JsonAlias("imdbRating") String assessmentRatingEpisode,
                          @JsonAlias("Runtime") String runTimeEpisode,
                          @JsonAlias("Released") String releasedEpisode) {
}
/*Este record pega os valores de:
titleNameEpisode - títuloEpisodio
numberEpisode - numeroEpisodio
assessmentRatingEpisode - avaliacaoEpsodio
runTimeEpisode - tempoEpisodio
String releasedEpisode - dataEpisodio
 */