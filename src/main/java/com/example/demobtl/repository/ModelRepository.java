package com.example.demobtl.repository;

import com.example.demobtl.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    @Query("SELECT m FROM Model m")
    List<Model> getListModelWithFilterContraints();
}
