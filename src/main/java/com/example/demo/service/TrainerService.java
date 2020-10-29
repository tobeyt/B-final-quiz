package com.example.demo.service;

import com.example.demo.dto.Trainer;
import com.example.demo.exception.CommonException;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<Trainer> getTrainers(boolean grouped) {
        return trainerRepository.findByGrouped(grouped);
    }

    public Trainer addNewTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public void deleteTrainerById(Long id) {
        trainerRepository.findById(id).orElseThrow(() -> new CommonException("讲师不存在"));
        trainerRepository.deleteById(id);
    }
}
