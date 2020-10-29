package com.example.demo.repository;

import com.example.demo.dto.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    List<Trainer> findByGrouped(boolean grouped);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE trainer SET group_id = NULL", nativeQuery = true)
    void clearGroupForeignKey();
}
