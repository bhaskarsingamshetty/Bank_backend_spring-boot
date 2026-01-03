package com.demo.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.learn.entity.Orders;
import com.demo.learn.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;
    @PostMapping()
    public ResponseEntity<Boolean> neworder(@RequestBody Orders order){
        return service.neworder(order);
    }
    @GetMapping()
    public ResponseEntity<List<Orders>> getall(){
        return service.getorders();
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Orders>> getbyid(@PathVariable Long id){
        return service.getbyid(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteorder(@PathVariable Long id){
        return service.deleteorder(id);
    }
}
