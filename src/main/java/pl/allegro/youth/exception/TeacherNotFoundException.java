package pl.allegro.youth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

<<<<<<< HEAD
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No found teacher")
public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(int teacherId) {
        super(String.format("Teacher id %d not found", teacherId));
=======
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(String teacherId) {
        super(String.format("Teacher id %s not found", teacherId));
>>>>>>> origin/CarokPl-tests
    }
}
