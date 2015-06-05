package pl.allegro.youth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.allegro.youth.model.Class;

import java.util.List;

public interface ClassRepository extends MongoRepository<Class, Integer> {

    List<Class> findByNumberBeforeAndTypeAfter(Integer number, char type);

}
