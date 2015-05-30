package pl.allegro.youth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.allegro.youth.model.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

}
