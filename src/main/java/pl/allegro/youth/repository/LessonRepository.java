package pl.allegro.youth.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import pl.allegro.youth.model.Lesson;



public interface LessonRepository extends MongoRepository<Lesson, Integer> {

    Lesson findByHourNumberAndAClassId(Integer hourNumber, Integer classId);

}
