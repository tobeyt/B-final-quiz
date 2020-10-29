package com.example.demo.repository;

import com.example.demo.dto.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    List<Trainee> findByGrouped(boolean grouped);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE trainee SET group_id = NULL", nativeQuery = true)
    void clearGroupForeignKey();
}
