package com.example.demo.service;

import com.example.demo.dto.Group;
import com.example.demo.dto.Trainee;
import com.example.demo.dto.Trainer;
import com.example.demo.exception.TrainerException;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private static final Integer TRAINER_NUMBER_OF_ONE_GROUP = 2;
    private static final String GROUP_NAME_SUFFIX = " 组";

    private final GroupRepository groupRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    private static List<Group> groups = new ArrayList<>();

    public GroupService(GroupRepository groupRepository, TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.groupRepository = groupRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public List<Group> getAllGroups() {
        return groups;
    }

    @Transactional
    // TODO GTB-知识点: + 分组时使用了事务注解，good
    // TODO GTB-工程实践: - 长方法，可以抽成多个方法提高可读性
    public List<Group> getAutoGroupingList() {
        initGroup();
        List<Trainer> allTrainer = trainerRepository.findAll();
        List<Trainee> allTrainee = traineeRepository.findAll();
        int trainerCount = allTrainer.size();
        int traineeCount = allTrainee.size();
        if (trainerCount < TRAINER_NUMBER_OF_ONE_GROUP) {
            throw new TrainerException("分组失败，讲师人数少于2人");
        }
        Collections.shuffle(allTrainer);
        Collections.shuffle(allTrainee);

        int groupNumber = trainerCount / TRAINER_NUMBER_OF_ONE_GROUP;
        int traineeNotGroupNumber = trainerCount % groupNumber;

        int hasGroupedTraineeNumber = 0;
        int hasGroupedTrainerNumber = 0;

        for (int groupId = 1; groupId <= groupNumber; groupId++) {
            Group group = new Group();
            group.setName(groupId + GROUP_NAME_SUFFIX);
            int groupedTraineeNumber = traineeCount / groupNumber;
            if (traineeNotGroupNumber > 0) {
                groupedTraineeNumber += 1;
                traineeNotGroupNumber -= 1;
            }
            List<Trainee> trainees = allTrainee.stream().skip(hasGroupedTraineeNumber).limit(groupedTraineeNumber)
                    .peek(trainee -> trainee.setGrouped(true)).collect(Collectors.toList());
            List<Trainer> trainers = allTrainer.stream().skip(hasGroupedTrainerNumber).limit(TRAINER_NUMBER_OF_ONE_GROUP)
                    .peek(trainer -> trainer.setGrouped(true)).collect(Collectors.toList());
            group.setTrainees(trainees);
            group.setTrainers(trainers);
            hasGroupedTraineeNumber += groupedTraineeNumber;
            hasGroupedTrainerNumber += TRAINER_NUMBER_OF_ONE_GROUP;

            groupRepository.save(group);
        }

        List<Group> groupList = groupRepository.findAll();

        groups = groupList;
        return groupList;
    }

    private void initGroup() {
        traineeRepository.clearGroupForeignKey();
        trainerRepository.clearGroupForeignKey();
        groupRepository.deleteAll();
    }
}
