package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Teacher getTeacher(@PathVariable Integer teacherId) {
        Teacher teacher = teacherRepository.findOne(teacherId);

        if (teacher == null) {
            throw new TeacherNotFoundException(teacherId);
        }

        return teacher;
    }

}
