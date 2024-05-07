package com.sintagma.sintagamaflix;

import com.sintagma.sintagamaflix.source.Start;
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
		Start start = new Start();
		start.displayMenu();
	}
}