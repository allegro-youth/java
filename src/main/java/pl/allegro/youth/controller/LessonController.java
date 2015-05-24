package pl.allegro.youth.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.allegro.youth.exception.LessonNotFoundException;
import pl.allegro.youth.model.Lesson;
import pl.allegro.youth.repository.LessonRepository;
import pl.allegro.youth.service.LessonService;


import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired

    private LessonService lessonService;

    @Autowired
    private LessonRepository lessonRepository;


    @RequestMapping(method = RequestMethod.GET)
    public List<Lesson> getLessons() {

        List<Lesson> lessons = lessonRepository.findAll();
        lessons = lessonService.getOtherLessonsObjects(lessons);
        return lessons;
    }

    @RequestMapping(value = "{lessonId}", method = RequestMethod.GET)
    public Lesson getLesson(@PathVariable Integer lessonId) {

        Lesson lesson = lessonRepository.findOne(lessonId);
        if (lesson == null) {
            throw new LessonNotFoundException(lessonId);
        }

        lesson = lessonService.getOtherLessonObjects(lesson);

        return lesson;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addLesson(@RequestBody Lesson lesson) {
        lessonRepository.save(lesson);
    }


    @RequestMapping(value = "{lessonId}", method = RequestMethod.POST)
    public void updateLesson(@RequestBody Lesson lesson, @PathVariable Integer lessonId) {
        lessonRepository.save(lesson);
    }



    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public List<Lesson> getCurrentLessons() {
        return lessonService.getCurrentLessons();
    }


    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public List<Lesson> getNextLessons() {
        return lessonService.getNextLessons();
    }



}
