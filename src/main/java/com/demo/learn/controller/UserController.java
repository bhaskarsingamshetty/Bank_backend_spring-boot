package com.demo.learn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.learn.entity.User;
import com.demo.learn.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @GetMapping()
    public ResponseEntity<List<User>> getusers(){
        Optional<List<User>> item = Optional.of(service.getusers());
        if(item.isPresent()){
            return ResponseEntity.ok(item.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public ResponseEntity<Boolean> adduser(@RequestBody User user){
        return service.adduser(user);
    }
}
