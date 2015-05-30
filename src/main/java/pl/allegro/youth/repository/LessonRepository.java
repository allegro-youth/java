package pl.allegro.youth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.allegro.youth.model.Lesson;

import java.util.List;

public interface LessonRepository extends MongoRepository<Lesson, Integer> {

    Lesson findByHourNumberAndAClassId(Integer hourNumber, Integer classId);
}
