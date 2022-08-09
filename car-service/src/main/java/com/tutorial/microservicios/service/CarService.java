package com.tutorial.microservicios.service;

import com.tutorial.microservicios.entity.Car;
import com.tutorial.microservicios.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> getAll(){
        return repository.findAll();
    }

    public Car getUserById(Integer userId){
        return repository.findById(userId).orElse(null);
    }

    public Car save(Car car){
        Car carNew = repository.save(car);
        return carNew;
    }

    public List<Car> byUserId(int userId){
        return repository.findByUserId(userId);
    }

    public void delete(){
        repository.deleteAll();
    }

}
