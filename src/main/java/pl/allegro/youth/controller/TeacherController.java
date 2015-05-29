package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.allegro.youth.exception.TeacherNotFoundException;
import pl.allegro.youth.model.*;
import pl.allegro.youth.repository.TeacherRepository;

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

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
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
