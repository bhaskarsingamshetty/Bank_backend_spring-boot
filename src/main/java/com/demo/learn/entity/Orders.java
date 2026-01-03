package com.demo.learn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long order_id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String product_name;

}
