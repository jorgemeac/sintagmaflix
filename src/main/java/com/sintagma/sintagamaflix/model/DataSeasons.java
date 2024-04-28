package com.sintagma.sintagamaflix.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataSeasons(@JsonAlias("Season") Integer numberesEpisodes,
                          @JsonAlias("Episodes") List<DataEpisode> episodes) {
}
