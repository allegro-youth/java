package pl.allegro.youth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.allegro.youth.model.Hour;

public interface HourRepository extends MongoRepository<Hour, Integer>{

    Hour findByStartBeforeAndEndAfter(Integer start, Integer end);
}
