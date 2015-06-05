package pl.allegro.youth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.allegro.youth.model.Teacher;

public interface TeacherRepository extends MongoRepository<Teacher, String> {

}
