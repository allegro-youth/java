package pl.allegro.youth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.youth.model.ClassRoom;

@RestController
@RequestMapping("/")
public class Controller {
    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
    @RequestMapping ("classroom")
    public ClassRoom classRoom (){
            ClassRoom classRoom = new ClassRoom();
            classRoom.setBuilding ("Budynek glowny");
            classRoom.setId (1);
            classRoom.setNr(1);
        return classRoom;
    }
}
