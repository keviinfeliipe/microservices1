package com.tutorial.microservicios.service;

import com.tutorial.microservicios.entity.Person;
import com.tutorial.microservicios.feignclient.BikeFeignClient;
import com.tutorial.microservicios.feignclient.CarFeignClient;
import com.tutorial.microservicios.model.Bike;
import com.tutorial.microservicios.model.Car;
import com.tutorial.microservicios.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PersonService {

    private final PersonRepository repository;
    private final RestTemplate template;
    private final CarFeignClient carFeignClient;
    private final BikeFeignClient bikeFeignClient;

    public PersonService(PersonRepository repository, RestTemplate template, CarFeignClient carFeignClient, BikeFeignClient bikeFeignClient) {
        this.repository = repository;
        this.template = template;
        this.carFeignClient = carFeignClient;
        this.bikeFeignClient = bikeFeignClient;
    }

    public List<Person> getAll(){
        return repository.findAll();
    }

    public Person getUserById(Integer userId){
        return repository.findById(userId).orElse(null);
    }

    public Person saveUser(Person user){
        return repository.save(user);
    }

    public List<Car> getCars(int userId){
        return template.getForObject("http://localhost:8002/car/byuser/"+userId, List.class);
    }

    public List<Bike> getBikes(int userId){
        return template.getForObject("http://localhost:8003/bike/byuser/"+userId, List.class);
    }

    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        return bikeFeignClient.save(bike);
    }

    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String, Object> response = new HashMap<>();
        var user = repository.findById(userId).orElse(null);
        if (user == null){
            response.put("Mensaje", "El usuario no existe.");
            return response;
        }
        response.put("User", user);
        var cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()){
            response.put("Cars", "El usuario no tiene carros");
        }else {
            response.put("Cars", cars);
        }
        var bikes = bikeFeignClient.getBikes(userId);
        if (bikes.isEmpty()){
            response.put("Bikes", "El usuario no tiene motos");
        }else {
            response.put("Bikes", bikes);
        }
        return response;
    }



}
