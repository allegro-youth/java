package pl.allegro.youth.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pl.allegro.youth.model.*;
import pl.allegro.youth.model.Class;

import java.util.List;

public interface HourRepository extends MongoRepository<Hour, Integer>{
    Hour findByStartBeforeAndEndAfter(Integer start, Integer end);


    void save(List<Class> classess);
}
