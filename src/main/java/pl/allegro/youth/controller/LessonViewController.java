package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.youth.model.Absence;
import pl.allegro.youth.model.Lesson;
import pl.allegro.youth.model.LessonView;
import pl.allegro.youth.repository.AbsenceRepository;
import pl.allegro.youth.service.LessonService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lessonsView")
public class LessonViewController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private AbsenceRepository absenceRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<LessonView> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        List<Absence> absences = absenceRepository.findAll();
        List<LessonView> lessonViews = new ArrayList<>();

        //TODO

        return lessonViews;
    }

}
