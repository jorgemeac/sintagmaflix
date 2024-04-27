package com.sintagma.sintagamaflix;

import com.sintagma.sintagamaflix.model.DataSeries;
import com.sintagma.sintagamaflix.service.ConsumerAPI;
import com.sintagma.sintagamaflix.service.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SintagamaflixApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SintagamaflixApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumerApi = new ConsumerAPI();
		var json = consumerApi.obterDados("https://www.omdbapi.com/?t=Supernatural&apikey=cdd26d82");
		System.out.println(json);
		ConvertData convertData = new ConvertData();
		DataSeries dataSeries = convertData.getData(json, DataSeries.class);
		System.out.println(dataSeries);
	}
}
