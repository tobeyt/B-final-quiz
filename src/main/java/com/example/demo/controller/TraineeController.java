package com.example.demo.controller;

import com.example.demo.dto.Trainee;
import com.example.demo.service.TraineeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trainee")
@CrossOrigin
public class TraineeController {
    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping
    public List<Trainee> getTraineesUnGrouped(@RequestParam(value = "grouped") boolean grouped) {
        return traineeService.getTrainees(grouped);
    }
}
