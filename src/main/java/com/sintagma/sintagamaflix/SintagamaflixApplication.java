package com.sintagma.sintagamaflix;

import com.sintagma.sintagamaflix.model.DataEpisode;
import com.sintagma.sintagamaflix.model.DataSeasons;
import com.sintagma.sintagamaflix.model.DataSeries;
import com.sintagma.sintagamaflix.service.ConsumerAPI;
import com.sintagma.sintagamaflix.service.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SintagamaflixApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SintagamaflixApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var consumerApi = new ConsumerAPI();
		var json = consumerApi.getData("https://www.omdbapi.com/?t=Supernatural&apikey=cdd26d82");
		System.out.println(json);
		ConvertData convertData = new ConvertData();
		DataSeries dataSeries = convertData.getData(json, DataSeries.class);
		System.out.println(dataSeries);

		json = consumerApi.getData("https://www.omdbapi.com/?t=supernatural&season=5&episode=2&apikey=cdd26d82");
		DataEpisode dataEpisode = convertData.getData(json, DataEpisode.class);
		System.out.println(dataEpisode);

		//Vou usar o for para imprimir uma lista de temporadas
		List<DataSeasons> seasons = new ArrayList<>();

		for (int i = 1; i <= dataSeries.seasonsTotal(); i++) {
			json = consumerApi.getData("https://www.omdbapi.com/?t=supernatural&season=" + i + "&apikey=cdd26d82");
			DataSeasons dataSeasons = convertData.getData(json, DataSeasons.class);
			seasons.add(dataSeasons);
		}
		seasons.forEach(System.out::println);
	}
}