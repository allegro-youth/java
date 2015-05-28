package pl.allegro.youth.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(String messageId) {
        super(String.format("Message id %s not found.", messageId));
    }
}
