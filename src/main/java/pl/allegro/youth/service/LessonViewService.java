package pl.allegro.youth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.allegro.youth.model.Absence;
import pl.allegro.youth.model.Lesson;
import pl.allegro.youth.model.LessonView;
import pl.allegro.youth.repository.AbsenceRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class LessonViewService {
    public static final int WORKING_HOURS_DEFAULT_VALUE = 1;
    @Autowired
    private LessonService lessonService;

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private HourService hourService;

    public List<LessonView> preparedLessons() {
        String room = "";
        Integer workingHours = WORKING_HOURS_DEFAULT_VALUE;
        List<Lesson> lessons = lessonService.getAllLessons();
        List<Absence> absences = absenceRepository.findAll();
        List<LessonView> lessonViews = new ArrayList<>();

        for (Lesson lesson : lessons) {
            LessonView lessonView = new LessonView();
            lessonView.setTeacher(lesson.getTeacher().showFullName());
            lessonView.setaClass(lesson.getaClass().showClassName());
            lessonView.setClassRoom(lesson.getClassRoom().showRoom());
            lessonView.setHours(lesson.getHour().showHours());

            for (Absence absence : absences) {
                if (lesson.equals(absence.getLesson())) {
                    lessonView.setLesson(lesson.getName());
                    if (lesson.getId() == absence.getLesson().getId()) {
                        lessonView.setInfo(absence.getInfo());
                        room = null;
                        break;
                    }
                }
            }
            lessonViews.add(lessonView);
            if (room.equals(lessonViews.toString())) {
                if (lesson.getHour().getNumber() > 0) {
                    System.out.println(hourService.countHours(lesson.getHour(), workingHours));
                }
                lessonViews.clear();
            }
        }
        return lessonViews;
    }


}
