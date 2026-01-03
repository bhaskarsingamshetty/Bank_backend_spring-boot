package com.demo.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.learn.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

}
