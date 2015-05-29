package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.allegro.youth.model.Hour;
import pl.allegro.youth.repository.HourRepository;

import java.util.List;

@RestController
@RequestMapping("/hour")
public class HourController {

    @Autowired
    private HourRepository hourRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Hour> getHours(){
        return hourRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Integer addHour(@RequestBody Hour hour){

        hourRepository.save(hour);
        return hour.getNumber();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Integer updateHour(@RequestBody Hour hour){

        hourRepository.save(hour);
        return hour.getNumber();

    }


}
