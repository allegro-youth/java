package pl.allegro.youth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.allegro.youth.controller.ClassController;
import pl.allegro.youth.controller.ClassRoomController;
import pl.allegro.youth.controller.HourController;
import pl.allegro.youth.controller.TeacherController;
import pl.allegro.youth.model.*;
import pl.allegro.youth.model.Class;
import pl.allegro.youth.repository.ClassRepository;
import pl.allegro.youth.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {

    private ClassRepository classRepository;
    private LessonRepository lessonRepository;
    private TeacherController teacherController;
    private ClassController classController;
    private ClassRoomController classRoomController;
    private HourController hourController;
    private HourService hourService;

    @Autowired
    public LessonService(ClassRepository classRepository, LessonRepository lessonRepository, TeacherController teacherController, ClassController classController, ClassRoomController classRoomController, HourController hourController, HourService hourService) {
        this.classRepository = classRepository;
        this.lessonRepository = lessonRepository;
        this.teacherController = teacherController;
        this.classController = classController;
        this.classRoomController = classRoomController;
        this.hourController = hourController;
        this.hourService = hourService;
    }




    public List<Lesson> getCurrentLessons() {
        Hour hour = hourService.getCurrentHour();

        if (hour != null) {
            return getLessonsByHour(hour);
        }

        return null;
    }

    public List<Lesson> getNextLessons() {
        Hour hour = hourService.getNextHour();

        if (hour != null) {
            return getLessonsByHour(hour);
        }

        return null;
    }

    private List<Lesson> getLessonsByHour(Hour hour){
        List<Lesson> lessons = new ArrayList<>();
        List<Class> classes = classRepository.findAll();

        for (Class aClass : classes) {
            Lesson lesson = lessonRepository.findByHourNumberAndAClassId(hour.getNumber(), aClass.getId());
            if (lesson != null) lessons.add(getOtherLessonObjects(lesson));
        }

        return lessons;
    }

    public List<Lesson> getOtherLessonsObjects(List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            lesson = getOtherLessonObjects(lesson);
        }
        return lessons;
    }

    public Lesson getOtherLessonObjects(Lesson lesson) {
        lesson.setTeacher(teacherController.getTeacher(lesson.getTeacher().getId()));
        lesson.setaClass(classController.getClass(lesson.getaClass().getId()));
        lesson.setClassRoom(classRoomController.getClassRoom(lesson.getClassRoom().getId()));
        lesson.setHour(hourController.getHour(lesson.getHour().getNumber()));
        return lesson;
    }


}