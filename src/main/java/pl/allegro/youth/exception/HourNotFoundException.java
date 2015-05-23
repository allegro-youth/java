package pl.allegro.youth.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found hour")
public class HourNotFoundException extends RuntimeException {

    public HourNotFoundException(Integer hourNumber) {
        super(String.format("Hour number %d not found.", hourNumber));
    }
}
