package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.youth.model.*;
import pl.allegro.youth.model.Class;
import pl.allegro.youth.repository.ClassRepository;

import java.util.List;

/**
 * Created by xsqunk on 23.05.15.
 */
@RestController
@RequestMapping ("/class")
public class ClassController {
    @Autowired
    private ClassRepository classRepository;

    @RequestMapping (method = RequestMethod.GET)
    public List <Class> getClas(){
        return classRepository.findAll();


    }




}
