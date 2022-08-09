package com.tutorial.microservicios.controller;

import com.tutorial.microservicios.entity.Car;
import com.tutorial.microservicios.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        var users = service.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable Integer id){
        var user = service.getUserById(id);
        if (user ==null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car){
        var userNew = service.save(car);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/byuser/{id}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("id") int userId){
        var cars = service.byUserId(userId);
        return ResponseEntity.ok(cars);
    }

    @DeleteMapping
    public ResponseEntity<Car> deleteAll(){
        service.delete();
        return ResponseEntity.noContent().build();
    }


}
