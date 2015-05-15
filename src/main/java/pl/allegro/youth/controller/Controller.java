package pl.allegro.youth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.youth.model.ClassRoom;
import pl.allegro.youth.model.Teacher;

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

    @RequestMapping("teacher")
    public String getTeachers(){
        Teacher first = new Teacher(1, "Andrzej", "Gac");
        Teacher secend = new Teacher(2, "Renata", "Gac");

        return first.toString() + " " + secend.toString();

    }

}
