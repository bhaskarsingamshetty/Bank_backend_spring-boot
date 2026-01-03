package com.demo.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.demo.learn.entity.User;
import com.demo.learn.repository.UserRepo;

@Component
public class UserService {

    @Autowired
    UserRepo repo;

    public List<User> getusers() {

        return repo.findAll();

    }

    public ResponseEntity<Boolean> adduser(User user) {
        try{
        repo.save(user);
        return ResponseEntity.ok(true);
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


}
