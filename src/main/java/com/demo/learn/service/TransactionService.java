package com.demo.learn.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;


import com.demo.learn.dto.ErrorResponse;
import com.demo.learn.dto.TransactionRequest;
import com.demo.learn.entity.Account;
import com.demo.learn.entity.Customer;
import com.demo.learn.repository.AccountRepo;

@Service
public class TransactionService {

    @Autowired
    private AccountRepo accrepo;
    @Transactional
    public ResponseEntity<?> dotransaction(TransactionRequest data) {

        Account sender = accrepo.findByAccountnumber(data.getSenderaccountnumber());
        Account receiver = accrepo.findByAccountnumber(data.getReceiveraccountnumber());

        if(sender==null)return ResponseEntity.status(404).body(new ErrorResponse("sender acount number not found"));


        String loggedInEmail = (String)SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal();

        Customer customer = sender.getCustomer();

        if (!customer.getEmail().equals(loggedInEmail)) return ResponseEntity.status(403).body(new ErrorResponse("You cannot transfer from this account"));

        if(data.getAmount()==null||data.getAmount()<=0)return ResponseEntity.badRequest().body(new ErrorResponse("enter valid amount"));

        if(receiver==null)return ResponseEntity.status(404).body(new ErrorResponse("receiver account number not found"));

        if(sender.getAccountnumber().equals(receiver.getAccountnumber()))return ResponseEntity.badRequest().body(new ErrorResponse("Cannot transfer to same account"));

        if(sender.getBalance()<data.getAmount())return ResponseEntity.badRequest().body(new ErrorResponse("Insufficient funds"));

        

        sender.setBalance(sender.getBalance()-data.getAmount());
        receiver.setBalance(receiver.getBalance()+data.getAmount());

        accrepo.save(sender);
        accrepo.save(receiver);
        return ResponseEntity.ok(Map.of("message","Transaction successful"));

    }

}
