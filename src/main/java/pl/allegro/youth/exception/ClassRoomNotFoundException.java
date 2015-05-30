package pl.allegro.youth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClassRoomNotFoundException extends RuntimeException {

    public ClassRoomNotFoundException(String classRoomId) {
        super(String.format("Classroom id %s not found.", classRoomId));

    }
}
