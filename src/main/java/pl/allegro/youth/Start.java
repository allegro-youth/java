package pl.allegro.youth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.allegro.youth.model.*;
import pl.allegro.youth.model.Class;
import pl.allegro.youth.repository.HourRepository;
import pl.allegro.youth.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Start implements CommandLineRunner {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private HourRepository hourRepository;

    @Autowired
    private HourRepository classRepository;

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        lessonRepository.deleteAll();

        hourRepository.deleteAll();

        classRepository.deleteAll();

        List<Hour> hours = new ArrayList<>();
        hours.add(new Hour(1, 815, 900));
        hours.add(new Hour(2, 905, 950));
        hours.add(new Hour(3, 955, 1040));
        hours.add(new Hour(4, 1045, 1130));
        hours.add(new Hour(5, 1145, 1230));
        hours.add(new Hour(6, 1235, 1320));
        hours.add(new Hour(7, 1325, 1410));
        hours.add(new Hour(8, 1430, 1515));
        hours.add(new Hour(9, 1520, 1605));
        hourRepository.save(hours);

        List <Class> classess = new ArrayList<>();
        classess.add(new Class(1,1,'H'));
        classRepository.save(classess);

        Teacher teacher = new Teacher(1, "Andrzej", "Gac", "AG");
        ClassRoom classRoom = new ClassRoom(1, "Budynek g��wny", 80);
        ClassRoom classRoom1 = new ClassRoom(2, "Budynek g��wny", 82);

        // fetch all customers
        System.out.println("Lessons found with findAll():");
        System.out.println("-------------------------------");
        lessonRepository.findAll().forEach(System.out::println);
        System.out.println();


        System.out.println("-------------------------------");
        lessonRepository.findByHourStartBeforeAndHourEndAfter(820, 820).forEach(System.out::println);
    }
}
