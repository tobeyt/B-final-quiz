package com.example.demo.repository;

import com.example.demo.dto.Trainee;
import com.example.demo.dto.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    List<Trainer> findByGrouped(boolean grouped);
}
