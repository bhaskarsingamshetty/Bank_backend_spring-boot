package com.demo.learn.dto;


import lombok.Data;

@Data
public class RequestPolicy {
    
    private Long productid;

    private Long customerid;

    private Double premiumAmount;
    
}
