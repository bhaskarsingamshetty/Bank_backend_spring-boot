package com.demo.learn.repository;

import com.demo.learn.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepo extends JpaRepository<Storage, Long> {

}
