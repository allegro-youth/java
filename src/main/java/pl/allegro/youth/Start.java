package pl.allegro.youth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.allegro.youth.controller.HourController;
import pl.allegro.youth.model.*;
import pl.allegro.youth.model.Class;

import pl.allegro.youth.repository.*;
import pl.allegro.youth.service.LessonService;


import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Start implements CommandLineRunner {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private HourRepository hourRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private HourController hourController;


    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*
        messageRepository.deleteAll();

        hourRepository.deleteAll();
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

        teacherRepository.deleteAll();
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1, "Andrzej", "Gac", "AG"));
        teachers.add(new Teacher(2, "Renata", "Gac", "RG"));
        teachers.add(new Teacher(3, "Violetta", "Kalka", "VK"));
        teachers.add(new Teacher(4, "Teresa", "Golińska", "TG"));
        teacherRepository.save(teachers);

        classRepository.deleteAll();
        List<Class> classes = new ArrayList<>();
        classes.add(new Class(1, 1, 'b'));
        classes.add(new Class(2, 1, 'd'));
        classes.add(new Class(3, 1, 'e'));
        classes.add(new Class(4, 1, 'h'));
        classes.add(new Class(5, 1, 'k'));
        classes.add(new Class(6, 1, 't'));

        classes.add(new Class(7, 2, 'b'));
        classes.add(new Class(8, 2, 'c'));
        classes.add(new Class(9, 2, 'd'));
        classes.add(new Class(10, 2, 'e'));
        classes.add(new Class(11, 2, 'h'));
        classes.add(new Class(12, 2, 'k'));
        classes.add(new Class(13, 2, 't'));

        classes.add(new Class(14, 3, 'd'));
        classes.add(new Class(15, 3, 'e'));
        classes.add(new Class(16, 3, 'h'));
        classes.add(new Class(17, 3, 'i'));
        classes.add(new Class(18, 3, 'k'));
        classes.add(new Class(19, 3, 't'));

        classes.add(new Class(20, 4, 'd'));
        classes.add(new Class(21, 4, 'e'));
        classes.add(new Class(22, 4, 'h'));
        classes.add(new Class(23, 4, 'i'));
        classes.add(new Class(24, 4, 'k'));
        classes.add(new Class(25, 4, 't'));
        classRepository.save(classes);

        classRoomRepository.deleteAll();
        ClassRoom classRoom = new ClassRoom(1, "Budynek główny", 80);
        classRoomRepository.save(classRoom);

        lessonRepository.deleteAll();
        lessonRepository.save(new Lesson(1, "Matematyka", "Mat",    teacherRepository.findOne(1), classRepository.findOne(1), classRoomRepository.findOne(1), hourRepository.findOne(1)));
        lessonRepository.save(new Lesson(2, "Informatyka", "Inf",   teacherRepository.findOne(2), classRepository.findOne(2), classRoomRepository.findOne(1), hourRepository.findOne(1)));
        lessonRepository.save(new Lesson(3, "J. polski", "Jpo",     teacherRepository.findOne(3), classRepository.findOne(3), classRoomRepository.findOne(1), hourRepository.findOne(1)));
        lessonRepository.save(new Lesson(4, "J. niemiecki", "Jni",  teacherRepository.findOne(4), classRepository.findOne(4), classRoomRepository.findOne(1), hourRepository.findOne(1)));
        lessonRepository.save(new Lesson(5, "J. angielski", "Jan",  teacherRepository.findOne(1), classRepository.findOne(5), classRoomRepository.findOne(1), hourRepository.findOne(1)));
        lessonRepository.save(new Lesson(6, "Historia", "His",      teacherRepository.findOne(2), classRepository.findOne(6), classRoomRepository.findOne(1), hourRepository.findOne(1)));

        lessonRepository.save(new Lesson(7, "J. niemiecki", "Jni",  teacherRepository.findOne(3), classRepository.findOne(1), classRoomRepository.findOne(1), hourRepository.findOne(2)));
        lessonRepository.save(new Lesson(8, "Historia", "His",      teacherRepository.findOne(4), classRepository.findOne(2), classRoomRepository.findOne(1), hourRepository.findOne(2)));
        lessonRepository.save(new Lesson(9, "J. angielski", "Jan",  teacherRepository.findOne(1), classRepository.findOne(3), classRoomRepository.findOne(1), hourRepository.findOne(2)));
        lessonRepository.save(new Lesson(10, "Matematyka", "Mat",   teacherRepository.findOne(2), classRepository.findOne(4), classRoomRepository.findOne(1), hourRepository.findOne(2)));
        lessonRepository.save(new Lesson(11, "J. polski", "Jpo",    teacherRepository.findOne(3), classRepository.findOne(5), classRoomRepository.findOne(1), hourRepository.findOne(2)));
        lessonRepository.save(new Lesson(12, "Informatyka", "Inf",  teacherRepository.findOne(4), classRepository.findOne(6), classRoomRepository.findOne(1), hourRepository.findOne(2)));

        lessonRepository.save(new Lesson(13, "J. polski", "Jpo",    teacherRepository.findOne(2), classRepository.findOne(1), classRoomRepository.findOne(1), hourRepository.findOne(3)));
        lessonRepository.save(new Lesson(14, "Historia", "His",     teacherRepository.findOne(3), classRepository.findOne(2), classRoomRepository.findOne(1), hourRepository.findOne(3)));
        lessonRepository.save(new Lesson(15, "J. niemiecki", "Jni", teacherRepository.findOne(4), classRepository.findOne(3), classRoomRepository.findOne(1), hourRepository.findOne(3)));
        lessonRepository.save(new Lesson(16, "Informatyka", "Inf",  teacherRepository.findOne(1), classRepository.findOne(4), classRoomRepository.findOne(1), hourRepository.findOne(3)));
        lessonRepository.save(new Lesson(17, "J. angielski", "Jan", teacherRepository.findOne(2), classRepository.findOne(5), classRoomRepository.findOne(1), hourRepository.findOne(3)));
        lessonRepository.save(new Lesson(18, "Matematyka", "Mat",   teacherRepository.findOne(3), classRepository.findOne(6), classRoomRepository.findOne(1), hourRepository.findOne(3)));

*/
        /*
        // fetch all lessons
        System.out.println("Lessons found with findAll():");
        System.out.println("-------------------------------");
        lessonRepository.findAll().forEach(System.out::println);
        System.out.println();

        System.out.println("----- All Hour ----------");
        hourRepository.findAll().forEach(System.out::println);
        System.out.println(hourController.getHour(1));

        System.out.println("-------Current Lessons-------");
        List<Lesson> currentLesson = lessonService.getCurrentLessons();
        if(currentLesson != null){
            currentLesson.forEach(System.out::println);
        }

        System.out.println("---------Next Lessons---------");
        List<Lesson> nextLesson = lessonService.getNextLessons();
        if(nextLesson != null){
            nextLesson.forEach(System.out::println);
        }

        */
    }
}
