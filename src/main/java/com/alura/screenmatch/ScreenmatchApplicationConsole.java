//package com.alura.screenmatch;
//import com.alura.screenmatch.main.Main;
//import com.alura.screenmatch.repository.EpisodeRepository;
//import com.alura.screenmatch.repository.SerieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ScreenmatchApplicationConsole implements CommandLineRunner {
//
//	@Autowired
//	private SerieRepository repository;
//	@Autowired
//	private EpisodeRepository episodeRepository;
//	public static void main(String[] args) {
//		SpringApplication.run(ScreenmatchApplicationConsole.class, args);
//	}
//
//	@Override
//	public void run(String... args) {
//	Main main = new Main(repository, episodeRepository);
//	main.mainCall();
//	}
//}
