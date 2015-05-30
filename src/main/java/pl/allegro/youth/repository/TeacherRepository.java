package pl.allegro.youth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.allegro.youth.model.Lesson;
import pl.allegro.youth.model.Teacher;

import java.util.List;

public interface TeacherRepository extends MongoRepository<Teacher, String> {

}
