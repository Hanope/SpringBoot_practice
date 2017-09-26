package com.example.demo.repository;

import com.example.demo.MyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {

    @Query("SELECT d FROM MyData d ORDER BY d.name")
    List<MyData> findAllOrderByName();
}
