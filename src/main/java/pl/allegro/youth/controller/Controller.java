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
    @RequestMapping ("classroom")
    public ClassRoom classRoom (){
            ClassRoom classRoom = new ClassRoom(1,"Budynek główny",1);
        return classRoom;
    }
    @RequestMapping ("teacher")
    public String teacher(){
        Teacher teacher = new Teacher ("Ktos","Ktosss",1);
        return teacher.toString();
    }
}
