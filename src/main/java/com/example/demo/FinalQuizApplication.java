package com.example.demo;

import com.example.demo.dto.Trainee;
import com.example.demo.dto.Trainer;
import com.example.demo.service.TraineeService;
import com.example.demo.service.TrainerService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO GTB-工程实践: * 分包合理
// TODO GTB-知识点: * 对lombok的掌握不错
// TODO GTB-知识点: * 对三层架构的掌握不错
// TODO GTB-知识点: * 对RESTful实践的掌握不错
// TODO GTB-知识点: * 对Spring MVC注解的掌握不错
// TODO GTB-知识点: * 对异常处理的掌握不错
// TODO GTB-完成度: * 没有进行异常校验
// TODO GTB-完成度: * 其它功能都完成了
// TODO GTB-测试: * 只有Controller测试，但覆盖相对全面
@SpringBootApplication
public class FinalQuizApplication implements ApplicationRunner {
    private final TraineeService traineeService;
    private final TrainerService trainerService;

    public FinalQuizApplication(TraineeService traineeService, TrainerService trainerService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalQuizApplication.class, args);
    }

    // TODO GTB-知识点: + 用ApplicationRunner初始化数据
    @Override
    public void run(ApplicationArguments args) throws Exception {
        traineeService.addNewTrainee(Trainee.builder().name("成吉思汗").build());
        traineeService.addNewTrainee(Trainee.builder().name("鲁班七号").build());
        traineeService.addNewTrainee(Trainee.builder().name("太乙真人").build());
        traineeService.addNewTrainee(Trainee.builder().name("钟无艳").build());
        traineeService.addNewTrainee(Trainee.builder().name("花木兰").build());
        traineeService.addNewTrainee(Trainee.builder().name("雅典娜").build());
        traineeService.addNewTrainee(Trainee.builder().name("芈月").build());
        traineeService.addNewTrainee(Trainee.builder().name("白起").build());
        traineeService.addNewTrainee(Trainee.builder().name("刘婵").build());
        traineeService.addNewTrainee(Trainee.builder().name("庄周").build());
        traineeService.addNewTrainee(Trainee.builder().name("马超").build());
        traineeService.addNewTrainee(Trainee.builder().name("哪吒").build());
        traineeService.addNewTrainee(Trainee.builder().name("大乔").build());
        traineeService.addNewTrainee(Trainee.builder().name("蔡文姬").build());

        trainerService.addNewTrainer(Trainer.builder().name("娟姐").build());
        trainerService.addNewTrainer(Trainer.builder().name("张钊").build());
        trainerService.addNewTrainer(Trainer.builder().name("程龙举").build());
        trainerService.addNewTrainer(Trainer.builder().name("张巍").build());
        trainerService.addNewTrainer(Trainer.builder().name("王晓峰").build());
        trainerService.addNewTrainer(Trainer.builder().name("彭梦秋").build());

    }
}
