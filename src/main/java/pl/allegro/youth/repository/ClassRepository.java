package pl.allegro.youth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.allegro.youth.model.*;
import pl.allegro.youth.model.Class;

public interface ClassRepository extends MongoRepository<Class, Integer> {

}
