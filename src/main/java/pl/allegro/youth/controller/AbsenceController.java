package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.allegro.youth.exception.AbsenceNotFoundException;
import pl.allegro.youth.model.Absence;
import pl.allegro.youth.repository.AbsenceRepository;

import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {

    @Autowired
    private AbsenceRepository absenceRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Absence> getAbsences() {
        return absenceRepository.findAll();
    }

    @RequestMapping(value = "{absenceId}", method = RequestMethod.GET)
    public Absence getAbsence(@PathVariable String absenceId) {

        Absence absence = absenceRepository.findOne(absenceId);
        if (absence == null) {
            throw new AbsenceNotFoundException(absenceId);
        }

        return absence;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addAbsence(@RequestBody Absence absence) {
        absenceRepository.save(absence);
    }

    @RequestMapping(value = "/{absenceId}", method = RequestMethod.DELETE)
    public void removeAbsence(@PathVariable String absenceId) {
        Absence absence = getAbsence(absenceId);
        absenceRepository.delete(absence);
    }

    @RequestMapping(value = "/{absenceId}", method = RequestMethod.POST)
    public void updateTeacher(@RequestBody Absence absence, @PathVariable String absenceId) {
        Absence oldAbsence = getAbsence(absenceId);
        oldAbsence.setInfo(absence.getInfo());
        oldAbsence.setLesson(absence.getLesson());
        absenceRepository.save(oldAbsence);
    }

}
