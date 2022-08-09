package com.tutorial.microservicios.controller;

import com.tutorial.microservicios.entity.Bike;
import com.tutorial.microservicios.service.BikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    private final BikeService service;

    public BikeController(BikeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
        var users = service.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable Integer id){
        var user = service.getUserById(id);
        if (user ==null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike user){
        var userNew = service.save(user);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/byuser/{id}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("id") int userId){
        var bikes = service.byUserId(userId);
        return ResponseEntity.ok(bikes);
    }

    @DeleteMapping
    public ResponseEntity<Bike> deleteAll(){
        service.delete();
        return ResponseEntity.noContent().build();
    }


}
