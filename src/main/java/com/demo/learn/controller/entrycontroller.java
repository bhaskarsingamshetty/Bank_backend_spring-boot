package com.demo.learn.controller;

import java.util.List;
import java.util.Optional;

import com.demo.learn.entity.Storage;
import com.demo.learn.service.EntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class entrycontroller {

    @Autowired
    private EntryService service;

    @GetMapping("/get-Storage")
    public ResponseEntity<List<Storage>> getAll(){
        Optional<List<Storage>> items= Optional.of(service.getAll());
        if(items.isPresent()){
            return ResponseEntity.ok(items.get());
        }else{
            return ResponseEntity.notFound().build();   
        }
    }

    @GetMapping("/get-Storage/{id}")
    public ResponseEntity<Storage> getAll(@PathVariable Long id){
        Optional<Storage> item= service.getEntryById(id);
        if(item.isPresent()){
            return ResponseEntity.ok(item.get());   
        }else{
            return ResponseEntity.notFound().build();  
        }

    }
    

    @PostMapping()
    public ResponseEntity<Boolean> createEntry(@RequestBody Storage item){
        try{
        service.saveEntry(item);
        return ResponseEntity.ok(true);
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> getEntryById(@PathVariable Long id, @RequestBody Storage item){
        return service.updateEntry(id,item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id){
        return service.deleteitem(id);
    }


}
