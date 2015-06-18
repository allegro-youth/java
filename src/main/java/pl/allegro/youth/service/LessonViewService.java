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
    @Autowired
    private LessonService lessonService;

    @Autowired
    private AbsenceRepository absenceRepository;

    public List<LessonView> showAllLessons() {
        return preparedLessons(lessonService.getAllLessons());
    }

    public List<LessonView> showCurrentLessons() {
        return preparedLessons(lessonService.getCurrentLessons());
    }

    private List<LessonView> preparedLessons(List<Lesson> lessons) {
        List<Absence> absences = absenceRepository.findAll();
        List<LessonView> lessonViews = new ArrayList<>();

        for (Lesson lesson : lessons) {
            LessonView lessonView = new LessonView();
            lessonView.setaClass(lesson.getaClass().showClassName());
            if (lesson.getHour() != null) {
                lessonView.setHours(lesson.getHour().showHours());
            }
            if (lesson.getName() != null) {
                lessonView.setLesson(lesson.getName());
                lessonView.setTeacher(lesson.getTeacher().showFullName());
                lessonView.setClassRoom(lesson.getClassRoom().showNumber());
                lessonView.setBuilding(lesson.getClassRoom().getBuilding());
            }
            for (Absence absence : absences) {
                if (lesson.getId() == absence.getLesson().getId()) {
                    lessonView.setInfo(absence.getInfo());
                    break;
                }
            }
            lessonViews.add(lessonView);
        }

        return lessonViews;
    }

}
