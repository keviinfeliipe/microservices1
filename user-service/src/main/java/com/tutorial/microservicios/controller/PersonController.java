package com.tutorial.microservicios.controller;

import com.tutorial.microservicios.entity.Person;
import com.tutorial.microservicios.model.Bike;
import com.tutorial.microservicios.model.Car;
import com.tutorial.microservicios.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class PersonController {
    private final PersonService service;
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll(){
        var users = service.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Integer id){
        var user = service.getUserById(id);
        if (user ==null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Person> saveUser(@RequestBody Person user){
        var userNew = service.saveUser(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("id") int userId){
        var user = service.getUserById(userId);
        if (user==null)
            return ResponseEntity.notFound().build();
        List<Car> cars = service.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{id}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("id") int userId){
        var user = service.getUserById(userId);
        if (user==null)
            return ResponseEntity.notFound().build();
        List<Bike> bikes = service.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car){
        if (service.getUserById(userId)== null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(service.saveCar(userId,car));
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike ){
        if (service.getUserById(userId)== null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(service.saveBike(userId, bike));
    }

    @GetMapping("/getall/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId){
        return ResponseEntity.ok(service.getUserAndVehicles(userId));
    }


}
