package pl.allegro.youth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.allegro.youth.model.Lesson;
import pl.allegro.youth.repository.LessonRepository;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Lesson> getLessons(){

        List<Lesson> lessons = lessonRepository.findAll();
        return lessons;
    }

    @RequestMapping(value = "{lessonId}", method = RequestMethod.GET)
    public Lesson getLesson(@PathVariable Integer lessonId){

        return lessonRepository.findOne(lessonId);
    }

}
