package pl.allegro.youth.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AbsenceNotFoundException extends  RuntimeException{
    public AbsenceNotFoundException(String absenceId) {
        super(String.format("Absence id %s not found.", absenceId));
    }
}
