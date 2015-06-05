package pl.allegro.youth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.allegro.youth.model.Absence;

public interface AbsenceRepository extends MongoRepository<Absence, String> {

}
