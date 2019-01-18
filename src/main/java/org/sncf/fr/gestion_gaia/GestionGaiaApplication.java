package org.sncf.fr.gestion_gaia;

import java.util.stream.Stream;

import org.sncf.fr.gestion_gaia.entites.Car;
import org.sncf.fr.gestion_gaia.repositories.CarRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionGaiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionGaiaApplication.class, args);
	}
	
	@Bean
    ApplicationRunner init(CarRepository carRepository) {
        return args -> {
            Stream.of("Monitoring;http://rffsrvsig30003:8888/site/index.htm", "Google;https://www.google.fr").forEach(name_url -> {
                Car car = new Car();
                String[] parts = name_url.split(";");
                String name = parts[0];
                String uri = parts[1];
                car.setName(name);
                car.setUrl(uri);
                carRepository.save(car);
            });          
            carRepository.findAll().forEach(System.out::println);
        };
    }

}

