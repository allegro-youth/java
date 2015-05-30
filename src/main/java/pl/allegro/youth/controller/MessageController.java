package pl.allegro.youth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.allegro.youth.exception.MessageNotFoundException;
import pl.allegro.youth.model.Message;
import pl.allegro.youth.repository.MessageRepository;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @RequestMapping(value = "/{messageId}", method = RequestMethod.GET)
    public Message getMessage(@PathVariable String messageId) {
        Message message = messageRepository.findOne(messageId);
        if (message == null) {
            throw new MessageNotFoundException(messageId);
        }
        return message;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addMessage(@RequestBody Message message) {
        messageRepository.save(message);
    }

    @RequestMapping(value = "/{messageId}", method = RequestMethod.POST)
    public void updateMessage(@RequestBody Message message, @PathVariable String messageId) {
        Message oldMessage = getMessage(messageId);
        oldMessage.setAddDate(message.getAddDate());
        oldMessage.setContent(message.getContent());
        oldMessage.setEndDate(message.getEndDate());
        messageRepository.save(oldMessage);
    }

    @RequestMapping(value = "/{messageId}", method = RequestMethod.DELETE)
    public void removeMessage(@PathVariable String messageId) {
        Message message = getMessage(messageId);
        messageRepository.delete(message);
    }

}
