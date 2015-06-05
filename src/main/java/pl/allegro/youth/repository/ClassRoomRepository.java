package pl.allegro.youth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.allegro.youth.model.ClassRoom;

public interface ClassRoomRepository extends MongoRepository<ClassRoom, String> {

}
