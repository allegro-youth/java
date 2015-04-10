package pl.allegro.youth.controller;

import pl.allegro.youth.model.Average;
import pl.allegro.youth.model.Marks;
import pl.allegro.youth.service.AverageMarks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SchoolController {

    private AverageMarks averageMarks;

    @Autowired
    public SchoolController(AverageMarks averageMarks) {
        this.averageMarks = averageMarks;
    }

    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public String school() {
        return "test";
    }

    @RequestMapping(value = "/lessons", method = RequestMethod.POST)
    public Average lessons(@RequestBody Marks marks) {
        return averageMarks.average(marks);
    }
}
