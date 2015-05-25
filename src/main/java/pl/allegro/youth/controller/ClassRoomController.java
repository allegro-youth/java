package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.allegro.youth.exception.ClassRoomNotFoundException;
import pl.allegro.youth.exception.HourNotFoundException;
import pl.allegro.youth.model.ClassRoom;
import pl.allegro.youth.repository.ClassRoomRepository;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassRoomController {

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<ClassRoom> getClassRooms(){
        return classRoomRepository.findAll();
    }

    @RequestMapping(value = "{classRoomId}", method = RequestMethod.GET)
    public ClassRoom getClassRoom(@PathVariable Integer classRoomId){
        ClassRoom classRoom = classRoomRepository.findOne(classRoomId);
        if (classRoom == null){
            throw new ClassRoomNotFoundException(classRoomId);
        }

        return classRoom;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addClassRoom(@RequestBody ClassRoom classRoom){
        classRoomRepository.save(classRoom);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateClassRoom(@RequestBody ClassRoom classRoom){
        classRoomRepository.save(classRoom);
    }

    @RequestMapping(value = "/{classroomId}", method = RequestMethod.DELETE)
    public void removeClass(@PathVariable Integer classroomId) {
        ClassRoom classRoom = getClassRoom(classroomId);
        classRoomRepository.delete(classRoom);
    }

}
