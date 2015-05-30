package pl.allegro.youth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LessonNotFoundException extends RuntimeException {

    public LessonNotFoundException(Integer lessonId) {
        super(String.format("Lesson id %d not found.", lessonId));
    }

}
