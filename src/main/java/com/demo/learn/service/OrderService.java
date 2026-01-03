package com.demo.learn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.demo.learn.entity.Orders;
import com.demo.learn.repository.OrderRepo;

@Component
public class OrderService {

    @Autowired
    private OrderRepo repo;
    public ResponseEntity<Boolean> neworder(Orders order) {
        try{
            repo.save(order);
            return ResponseEntity.ok(true);
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
    public ResponseEntity<List<Orders>> getorders() {
        List<Orders> item = repo.findAll();
        if(item.isEmpty()){
            return ResponseEntity.noContent() .build();
        }
            return ResponseEntity.ok(item);
    }
    public ResponseEntity<List<Orders>> getbyid(Long id) {
        Optional<List<Orders>> item = Optional.of(repo.findByUser_userId(id));
        if(item.isPresent()){
            return ResponseEntity.ok(item.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Boolean> deleteorder(Long id) {
        if(repo.existsById(id)){
        repo.deleteById(id);
        return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
