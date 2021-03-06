package pl.allegro.youth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.allegro.youth.controller.HourController;
import pl.allegro.youth.model.Absence;
import pl.allegro.youth.model.Class;
import pl.allegro.youth.model.ClassRoom;
import pl.allegro.youth.model.Hour;
import pl.allegro.youth.model.Lesson;
import pl.allegro.youth.model.Teacher;
import pl.allegro.youth.repository.AbsenceRepository;
import pl.allegro.youth.repository.ClassRepository;
import pl.allegro.youth.repository.ClassRoomRepository;
import pl.allegro.youth.repository.HourRepository;
import pl.allegro.youth.repository.LessonRepository;
import pl.allegro.youth.repository.MessageRepository;
import pl.allegro.youth.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitDataService {
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

    @Autowired
    private AbsenceRepository absenceRepository;


    public void init() {
        absenceRepository.deleteAll();

        messageRepository.deleteAll();

        lessonRepository.deleteAll();

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
        teachers.add(new Teacher("Andrzej", "Gac", "AG"));
        teachers.add(new Teacher("Renata", "Gac", "RG"));
        teachers.add(new Teacher("Violetta", "Kalka", "VK"));
        teachers.add(new Teacher("Teresa", "Golińska", "TG"));
        teacherRepository.save(teachers);

        classRepository.deleteAll();
        List<pl.allegro.youth.model.Class> classes = new ArrayList<>();
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
        ClassRoom classRoom = new ClassRoom("Budynek główny", 80);
        classRoomRepository.save(classRoom);

        lessonRepository.deleteAll();
        lessonRepository.save(new Lesson(1, "Matematyka", "Mat", teachers.get(1), classRepository.findOne(1), classRoom, hourRepository.findOne(1)));
        lessonRepository.save(new Lesson(2, "Informatyka", "Inf", teachers.get(2), classRepository.findOne(2), classRoom, hourRepository.findOne(1)));
        lessonRepository.save(new Lesson(3, "J. polski", "Jpo", teachers.get(3), classRepository.findOne(3), classRoom, hourRepository.findOne(1)));
        lessonRepository.save(new Lesson(4, "J. niemiecki", "Jni", teachers.get(0), classRepository.findOne(4), classRoom, hourRepository.findOne(1)));
        lessonRepository.save(new Lesson(5, "J. angielski", "Jan", teachers.get(1), classRepository.findOne(5), classRoom, hourRepository.findOne(1)));
        lessonRepository.save(new Lesson(6, "Historia", "His", teachers.get(2), classRepository.findOne(6), classRoom, hourRepository.findOne(1)));

        lessonRepository.save(new Lesson(7, "J. niemiecki", "Jni", teachers.get(3), classRepository.findOne(1), classRoom, hourRepository.findOne(2)));
        lessonRepository.save(new Lesson(8, "Historia", "His", teachers.get(0), classRepository.findOne(2), classRoom, hourRepository.findOne(2)));
        lessonRepository.save(new Lesson(9, "J. angielski", "Jan", teachers.get(1), classRepository.findOne(3), classRoom, hourRepository.findOne(2)));
        lessonRepository.save(new Lesson(10, "Matematyka", "Mat", teachers.get(2), classRepository.findOne(4), classRoom, hourRepository.findOne(2)));
        lessonRepository.save(new Lesson(11, "J. polski", "Jpo", teachers.get(3), classRepository.findOne(5), classRoom, hourRepository.findOne(2)));
        lessonRepository.save(new Lesson(12, "Informatyka", "Inf", teachers.get(0), classRepository.findOne(6), classRoom, hourRepository.findOne(2)));

        lessonRepository.save(new Lesson(13, "J. polski", "Jpo", teachers.get(2), classRepository.findOne(1), classRoom, hourRepository.findOne(3)));
        lessonRepository.save(new Lesson(14, "Historia", "His", teachers.get(3), classRepository.findOne(2), classRoom, hourRepository.findOne(3)));
        lessonRepository.save(new Lesson(15, "J. niemiecki", "Jni", teachers.get(0), classRepository.findOne(3), classRoom, hourRepository.findOne(3)));
        lessonRepository.save(new Lesson(16, "Informatyka", "Inf", teachers.get(1), classRepository.findOne(4), classRoom, hourRepository.findOne(3)));
        lessonRepository.save(new Lesson(17, "J. angielski", "Jan", teachers.get(2), classRepository.findOne(5), classRoom, hourRepository.findOne(3)));
        Lesson lesson = new Lesson(18, "Matematyka", "Mat", teachers.get(3), classRepository.findOne(6), classRoom, hourRepository.findOne(3));
        lessonRepository.save(lesson);


        absenceRepository.save(new Absence(lesson, "Do swietlicy"));
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
