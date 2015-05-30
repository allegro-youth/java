package pl.allegro.youth.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import pl.allegro.youth.model.Lesson;
import pl.allegro.youth.model.Teacher;

import java.util.List;

<<<<<<< HEAD
public interface TeacherRepository extends MongoRepository<Teacher, Integer> {
=======
public interface TeacherRepository extends MongoRepository<Teacher, String> {
>>>>>>> origin/CarokPl-tests

}
