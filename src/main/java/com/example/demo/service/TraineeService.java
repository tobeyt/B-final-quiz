package com.example.demo.service;

import com.example.demo.dto.Trainee;
import com.example.demo.exception.CommonException;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public List<Trainee> getTrainees(boolean grouped) {
        return traineeRepository.findByGrouped(grouped);
    }

    public Trainee addNewTrainee(Trainee trainee) {
        return traineeRepository.save(trainee);
    }

    public void deleteTraineeById(Long id) {
        traineeRepository.findById(id).orElseThrow(() -> new CommonException("学员不存在"));
        traineeRepository.deleteById(id);
    }
}
