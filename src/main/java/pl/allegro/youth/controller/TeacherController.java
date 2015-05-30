package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.youth.exception.TeacherNotFoundException;
import pl.allegro.youth.model.*;
import pl.allegro.youth.model.Class;
import pl.allegro.youth.repository.TeacherRepository;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;
=======
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.allegro.youth.exception.TeacherNotFoundException;
import pl.allegro.youth.model.*;
import pl.allegro.youth.repository.TeacherRepository;

>>>>>>> origin/CarokPl-tests
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }

    @RequestMapping(value = "{teacherId}", method = RequestMethod.GET)
    public Teacher getTeacher(@PathVariable String teacherId) {

        Teacher teacher = teacherRepository.findOne(teacherId);
        if (teacher == null) {
            throw new TeacherNotFoundException(teacherId);
        }

        return teacher;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeacher(@RequestBody Teacher teacher){
        teacherRepository.save(teacher);
    }

    @RequestMapping(value = "/{teacherId}", method = RequestMethod.DELETE)
    public void removeTeacher(@PathVariable String teacherId){
        Teacher teacher = getTeacher(teacherId);
        teacherRepository.delete(teacher);
    }

    @RequestMapping(value = "/{teacherId}", method = RequestMethod.POST)
    public void updateTeacher(@RequestBody Teacher teacher, @PathVariable String teacherId){
        Teacher oldTeacher = getTeacher(teacherId);
        oldTeacher.setId(teacher.getId());
        oldTeacher.setFirstName(teacher.getFirstName());
        oldTeacher.setLastName(teacher.getLastName());
        oldTeacher.setShortName(teacher.getShortName());
        teacherRepository.save(oldTeacher);
    }

}
