package hello.controller;

import com.fasterxml.jackson.annotation.JacksonInject;
import hello.model.Average;
import hello.model.Greeting;
import hello.model.Marks;
import hello.service.AverageMarks;
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
