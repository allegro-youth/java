/*
package pl.allegro.youth.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.allegro.youth.Start;
import pl.allegro.youth.model.Message;
import pl.allegro.youth.repository.MessageRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.jayway.restassured.RestAssured.expect;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Start.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MessageControllerTest {

    private List<Message> messages;
    private Gson gson;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageController messageController;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        messageRepository.deleteAll();
        messages = new ArrayList<>();
        Long date = new Date().getTime();
        messages.add(new Message(date, date, "Tresc pierwszej widomosc"));
        messages.add(new Message(date, date, "Tresc drugiej widomosc"));
        messages.add(new Message(date, date, "Tresc trzeciej widomosc"));
        messageRepository.save(messages);


        gson = new GsonBuilder()
                .serializeNulls()
                .create();
        RestAssured.port = port;
    }

    @Test
    public void shouldReturnMessagesList() throws Exception {
        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(gson.toJson(messages)))
                .when()
                .get("/messages");

    }

    @Test
    public void shouldReturnMessageById() throws Exception {
        Message message = messages.get(0);
        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(gson.toJson(message)))
                .when()
                .get("/messages/{messageId}", message.getId());
    }

    @Test
    public void shouldReturnStatusCode404() throws Exception {
        String notExistId = "u21b3h12b4j1b2y4";

        expect()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", equalTo(String.format("Message id %s not found.", notExistId)))
                .when()
                .get("/messages/{messageId}", notExistId);
    }

    @Test
    public void shouldAddNewMessage() throws Exception {
        Long date = new Date().getTime();
        Message message = new Message(date, date, "Nowa wiadomosc");
        expect()
                .statusCode(HttpStatus.SC_CREATED)
                .body(equalTo(""))
                .given()
                .contentType(ContentType.JSON)
                .body(message)
                .when()
                .put("/messages");
    }

    @Test
    public void shouldRemoveMessageById() throws Exception {

        Message message = messageRepository.findAll().get(0);
        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(""))
                .when()
                .delete("/messages/{messageId}", message.getId());

        Message removeMessage = messageRepository.findOne(message.getId());
        assertThat(removeMessage).isNull();
    }

    @Test
    public void shouldUpdateMessageById() throws Exception {
        Message message = messageRepository.findAll().get(0);
        message.setContent("Nowa zawartosc");

        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(""))
                .given()
                .contentType(ContentType.JSON)
                .body(message)
                .when()
                .post("/messages/{messageId}", message.getId());

        Message updateMessage = messageRepository.findOne(message.getId());
        assertThat(updateMessage).isEqualTo(message);
    }
}*/
