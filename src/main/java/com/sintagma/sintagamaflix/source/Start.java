package com.sintagma.sintagamaflix.source;

import com.sintagma.sintagamaflix.model.DataEpisode;
import com.sintagma.sintagamaflix.model.DataSeasons;
import com.sintagma.sintagamaflix.model.DataSeries;
import com.sintagma.sintagamaflix.model.Episode;
import com.sintagma.sintagamaflix.service.ConsumerAPI;
import com.sintagma.sintagamaflix.service.ConvertData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Start {

    private Scanner read = new Scanner(System.in);
    private ConsumerAPI consumerAPI = new ConsumerAPI();
    private ConvertData convertData = new ConvertData();

    private final String ADDRESS_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=cdd26d82";

    public void displayMenu(){
        System.out.println("Qual série deseja pesquisar?");
        var nameSeries = read.nextLine();
        var json = consumerAPI.getData(ADDRESS_URL + nameSeries.replace(" ", "+") + API_KEY);

        DataSeries dataSeries = convertData.getData(json, DataSeries.class);
        System.out.println(dataSeries);

        //Vou usar o for para imprimir uma lista de temporadas
        List<DataSeasons> seasons = new ArrayList<>();

        for (int i = 1; i <= dataSeries.seasonsTotal(); i++) {
            json = consumerAPI.getData(ADDRESS_URL + nameSeries.replace(" ", "+") + "&season=" + i + API_KEY);
            DataSeasons dataSeasons = convertData.getData(json, DataSeasons.class);
            seasons.add(dataSeasons);
        }
//        seasons.forEach(System.out::println); //função lâmbda para um único parâmetro.

//        for (int i = 0; i < dataSeries.seasonsTotal(); i++) {
//            List<DataEpisode> seasonEpisodes = seasons.get(i).episodes();
//            for (int j = 0; j < seasonEpisodes.size(); j++) {
//                System.out.println(seasonEpisodes.get(j).titleNameEpisode());
//            }
//        }
        //A linha de código abaixo é uma função lâmbda que simplifica o for acima.

        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.titleNameEpisode())));

        List<DataEpisode> dataEpisodes = seasons.stream()
                .flatMap(s -> s.episodes().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episódios:");

//        dataEpisodes.stream()
//                .filter(e -> !e.assessmentRatingEpisode().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("primeiro filtro (N/A) " + e))
//                .sorted(Comparator.comparing(DataEpisode::assessmentRatingEpisode).reversed())
//                .peek(e -> System.out.println("Ordenação " + e))
//                .limit(10)
//                .peek(e -> System.out.println("Limite de 10 " + e))
//                .map(e -> e.titleNameEpisode().toUpperCase())
//                .peek(e -> System.out.println("Mapeamento " + e))
//                .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                        .map(d -> new Episode(s.numberesEpisodes(), d))
                ).collect(Collectors.toList());

        episodes.forEach(System.out::println);

//        System.out.println("Digite o nome da Série: ");
//        var excerptTitle = read.nextLine(); //excerptTitle - trecho do título
//        Optional<Episode> episodeSearch = episodes.stream()
//                .filter(e -> e.getTitleNameEpisode().toUpperCase().contains(excerptTitle.toUpperCase()))
//                .findFirst();
//
//        if(episodeSearch.isPresent()){
//            System.out.println("Episódio Encontrado!");
//            System.out.println("Temporada" + episodeSearch.get().getSeasons());
//        } else {
//            System.out.println("Episódio não encontrado!");
//        }
//
//        System.out.println("A partir de que ano deseja ver o epsódio? ");
//        var year = read.nextLine(); //leitura do ano
//        read.nextLine();
//
//        LocalDate dateSearch = LocalDate.of(Integer.parseInt(year), 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodes.stream()
//                .filter(e -> e.getReleasedEpisode() != null && e.getReleasedEpisode().isAfter(dateSearch))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getSeasons() +
//                                ", Episódio: " + e.getTitleNameEpisode() +
//                                ", Data de lançamento: " + e.getReleasedEpisode().atStartOfDay()
//                ));


        //Um mapa para avaliar as temporadas (reviewBySeason - avaliacoPorTemporada
        Map<Integer, Double> reviewBySeason = episodes.stream()
                .filter(e -> e.getAssessmentRatingEpisode() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeasons,
                         Collectors.averagingDouble(Episode::getAssessmentRatingEpisode)));
        System.out.println(reviewBySeason);

        DoubleSummaryStatistics statistic = episodes.stream()
                .filter(e -> e.getAssessmentRatingEpisode() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getAssessmentRatingEpisode));
        System.out.println("Média: " + statistic.getAverage());
        System.out.println("Pior Avaliado: " + statistic.getMin());
        System.out.println("Melhor Avaliado: " + statistic.getMax());
        System.out.println("Quantidade: " + statistic.getCount());
    }
}

