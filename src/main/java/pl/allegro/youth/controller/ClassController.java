package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.allegro.youth.exception.ClassNotFoundException;
import pl.allegro.youth.model.Class;
import pl.allegro.youth.repository.ClassRepository;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassRepository classRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Class> getClasses() {
        return classRepository.findAll();
    }

    @RequestMapping(value = "{classId}", method = RequestMethod.GET)
    public Class getClass(@PathVariable Integer classId) {
        Class aClass = classRepository.findOne(classId);
        if (aClass == null) {
            throw new ClassNotFoundException(classId);
        }

        return aClass;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addClass(@RequestBody Class aClass) {
        classRepository.save(aClass);
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.POST)
    public void updateClass(@RequestBody Class aClass, @PathVariable Integer classId) {
        Class oldClass = getClass(classId);
        oldClass.setId(aClass.getId());
        oldClass.setType(aClass.getType());
        oldClass.setNumber(aClass.getNumber());
        classRepository.save(oldClass);
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.DELETE)
    public void removeClass(@PathVariable Integer classId) {
        Class aClass = classRepository.findOne(classId);
        classRepository.delete(aClass);
    }

}
