package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.youth.model.LessonView;
import pl.allegro.youth.service.LessonViewService;

import java.util.List;

@RestController
@RequestMapping("/lessonsView")
public class LessonViewController {

    @Autowired
    private LessonViewService lessonViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<LessonView> getAllLessons() {
        return lessonViewService.showAllLessons();

    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public List<LessonView> getCurrentLessons(){
        return lessonViewService.showCurrentLessons();
    }

}
