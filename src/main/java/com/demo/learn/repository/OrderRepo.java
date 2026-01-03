package com.demo.learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.learn.entity.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders,Long>{

    List<Orders> findByUser_userId(Long userId);
}
