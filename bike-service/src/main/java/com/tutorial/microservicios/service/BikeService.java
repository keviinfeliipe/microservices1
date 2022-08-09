package com.tutorial.microservicios.service;

import com.tutorial.microservicios.entity.Bike;
import com.tutorial.microservicios.repository.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    private final BikeRepository repository;

    public BikeService(BikeRepository repository) {
        this.repository = repository;
    }

    public List<Bike> getAll(){
        return repository.findAll();
    }

    public Bike getUserById(Integer userId){
        return repository.findById(userId).orElse(null);
    }

    public Bike save(Bike car){
        Bike carNew = repository.save(car);
        return carNew;
    }

    public List<Bike> byUserId(int userId){
        return repository.findByUserId(userId);
    }

    public void delete(){
        repository.deleteAll();
    }

}
