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
        return lessonService.getOtherLessonsObjects(lessons);
    }

    @RequestMapping(value = "{lessonId}", method = RequestMethod.GET)
    public Lesson getLesson(@PathVariable Integer lessonId) {
        Lesson lesson = lessonRepository.findOne(lessonId);
        if (lesson == null) {
            throw new LessonNotFoundException(lessonId);
        }
        return lessonService.getOtherLessonObjects(lesson);
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

    @RequestMapping(value = "/{lessonId}", method = RequestMethod.DELETE)
    public void removeLesson(@PathVariable Integer lessonId) {
        Lesson lesson = getLesson(lessonId);
        lessonRepository.delete(lessonId);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public List<Lesson> getCurrentLessons() {
        return lessonService.getCurrentLessons();
    }


    @RequestMapping(value = "/currentAndNext", method = RequestMethod.GET)
    public List<List<Lesson>> getCurrentAndNextLessons() {
        return lessonService.getCurrentAndNextLessons();
    }

    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public List<Lesson> getNextLessons() {
        return lessonService.getNextLessons();
    }

}
