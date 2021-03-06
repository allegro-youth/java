package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.allegro.youth.exception.HourNotFoundException;
import pl.allegro.youth.model.Hour;
import pl.allegro.youth.repository.HourRepository;
import pl.allegro.youth.service.HourService;

import java.util.List;

@RestController
@RequestMapping("/hours")
public class HourController {

    @Autowired
    private HourRepository hourRepository;

    @Autowired
    private HourService hourService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Hour> getHours() {
        return hourRepository.findAll();
    }

    @RequestMapping(value = "{hourNumber}", method = RequestMethod.GET)
    public Hour getHour(@PathVariable Integer hourNumber) {
        Hour hour = hourRepository.findOne(hourNumber);
        if (hour == null) {
            throw new HourNotFoundException(hourNumber);
        }
        return hour;
    }

    @RequestMapping(value = "/{hourId}", method = RequestMethod.DELETE)
    public void removeHour(@PathVariable Integer hourId) {
        Hour hour = getHour(hourId);
        hourRepository.delete(hour);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Hour getCurrentHour() {
        return hourService.getCurrentHour();
    }

    @RequestMapping(value = "/next", method = RequestMethod.GET)
    public Hour getNextHour() {
        return hourService.getNextHour();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addHour(@RequestBody Hour hour) {
        hourRepository.save(hour);
    }

    @RequestMapping(value = "/{hourId}", method = RequestMethod.POST)
    public void updateHour(@RequestBody Hour hour, @PathVariable Integer hourId) {
        Hour oldHour = getHour(hourId);
        oldHour.setNumber(hour.getNumber());
        oldHour.setStart(hour.getStart());
        oldHour.setEnd(hour.getEnd());
        hourRepository.save(oldHour);
    }

}
