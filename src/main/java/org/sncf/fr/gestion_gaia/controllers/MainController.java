package org.sncf.fr.gestion_gaia.controllers;

import java.util.Collection;
import java.util.stream.Collectors;

import org.sncf.fr.gestion_gaia.entites.Car;
import org.sncf.fr.gestion_gaia.repositories.CarRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MainController {
    private CarRepository carRepository;

    public MainController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cool-cars")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<Car> coolCars() {
        return carRepository.findAll().stream()
                .filter(this::isCool)
                .collect(Collectors.toList());
    }

    private boolean isCool(Car car) {
        return !car.getName().equals("XXX");
    }
   
}
