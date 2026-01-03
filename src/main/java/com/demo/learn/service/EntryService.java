package com.demo.learn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.demo.learn.entity.Storage;
import com.demo.learn.repository.EntryRepo;

@Component
public class EntryService {

    @Autowired
    private EntryRepo repo;
     public void saveEntry(Storage item){
        repo.save(item);
        
     }
    public List<Storage> getAll(){
        return repo.findAll();
    }
    public ResponseEntity<Boolean> updateEntry(Long id, Storage item) {
        if(repo.findById(id)!=null){
        Storage part = repo.findById(id).orElseThrow();
        if(item.getName()!=null)
        part.setName(item.getName());
        if(item.getContent()!=null)
        part.setContent(item.getContent());
        repo.save(part);
        return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }
    public Optional<Storage> getEntryById(Long  id) {
        return repo.findById(id);
    }
    public ResponseEntity<Void> deleteitem(Long id) {
        if(repo.existsById(id)){
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

}
