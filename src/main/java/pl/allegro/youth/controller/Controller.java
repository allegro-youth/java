package pl.allegro.youth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.youth.model.*;
import pl.allegro.youth.model.Class;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {
    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("classroom")
    public ClassRoom classRoom(){
        return new ClassRoom(1, "Budynek główny", 70);

    }
/*

    @RequestMapping("lesson")
    public Lesson getLesson(){
        Teacher teacher = new Teacher(1, "Andrzej", "Gac", "AG");
        pl.allegro.youth.model.Class aClass = new Class(1, 4, 'K');
        ClassRoom classRoom = new ClassRoom(1, "Budynek główny", 80);
        Hour hour = new Hour(1, 815, 900);

        Lesson lesson = new Lesson(1, "Matematyka", "Mat" , teacher,  aClass, classRoom, hour);

        return lesson;
    }*/

    @RequestMapping("teacher")
    public List<Teacher> getTeachers(){
        Teacher first = new Teacher(1, "Andrzej", "Gac", "AG");
        Teacher secend = new Teacher(2, "Renata", "Gac", "RG");

        return new ArrayList<Teacher>(Arrays.asList(first,secend));

    }

}
