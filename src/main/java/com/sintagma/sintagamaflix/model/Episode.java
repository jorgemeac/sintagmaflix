package com.sintagma.sintagamaflix.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.lang.Double.valueOf;
import static java.lang.Integer.parseInt;


public class Episode {
    private Integer seasons; //temporada
    private String titleNameEpisode; //nome do título do episódio
    private Integer numberEpisode; //número do episódio
    private Double assessmentRatingEpisode; //avaliação do episódio
    private Double runTimeEpisode; //tempo de duração
    private LocalDate releasedEpisode; //data
    //Construtor
    public Episode(Integer numberSeasons, DataEpisode dataEpisode) {
        this.seasons = numberSeasons;
        this.titleNameEpisode = dataEpisode.titleNameEpisode();
        this.numberEpisode = dataEpisode.numberEpisode();

        try {
            this.assessmentRatingEpisode = Double.valueOf(dataEpisode.assessmentRatingEpisode());
        } catch (NumberFormatException ex) {
            this.assessmentRatingEpisode = 0.0;
        }

        try {
            this.releasedEpisode = LocalDate.parse(dataEpisode.releasedEpisode());
        } catch (DateTimeParseException ex) {
            this.releasedEpisode = null;
        }

        // Verifica se runTimeEpisode não é nulo antes de tentar converter em Double
        if (dataEpisode.runTimeEpisode() != null) {
            try {
                this.runTimeEpisode = Double.valueOf(dataEpisode.runTimeEpisode());
            } catch (NumberFormatException ex) {
                this.runTimeEpisode = 0.0;
            }
        } else {
            this.runTimeEpisode = 0.0; // Define um valor padrão caso runTimeEpisode seja nulo
        }
    }

    //Geters e Setres
    public Double getAssessmentRatingEpisode() {
        return assessmentRatingEpisode;
    }

    public void setAssessmentRatingEpisode(Double assessmentRatingEpisode) {
        this.assessmentRatingEpisode = assessmentRatingEpisode;
    }

    public Integer getNumberEpisode() {
        return numberEpisode;
    }

    public void setNumberEpisode(Integer numberEpisode) {
        this.numberEpisode = numberEpisode;
    }

    public LocalDate getReleasedEpisode() {
        return releasedEpisode;
    }

    public void setReleasedEpisode(LocalDate releasedEpisode) {
        this.releasedEpisode = releasedEpisode;
    }

    public Double getRunTimeEpisode() {
        return runTimeEpisode;
    }

    public void setRunTimeEpisode(Double runTimeEpisode) {
        this.runTimeEpisode = runTimeEpisode;
    }

    public Integer getSeasons() {
        return seasons;
    }

    public void setSeasons(Integer seasons) {
        this.seasons = seasons;
    }

    public String getTitleNameEpisode() {
        return titleNameEpisode;
    }

    public void setTitleNameEpisode(String titleNameEpisode) {
        this.titleNameEpisode = titleNameEpisode;
    }

    //toString

    @Override
    public String toString() {
        return  "seasons= " + seasons +
                ", titleNameEpisode= '" + titleNameEpisode + '\'' +
                ", numberEpisode= " + numberEpisode +
                ", releasedEpisode= " + releasedEpisode +
                ", assessmentRatingEpisode= " + assessmentRatingEpisode +
                ", runTimeEpisode= " + runTimeEpisode;
    }
}
