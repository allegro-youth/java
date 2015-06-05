package pl.allegro.youth.controller;

import com.google.gson.Gson;
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
import pl.allegro.youth.model.ClassRoom;
import pl.allegro.youth.repository.ClassRoomRepository;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.expect;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Start.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ClassRoomControllerTest {

    private List<ClassRoom> classRooms;
    private Gson gson;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        classRoomRepository.deleteAll();
        classRooms = new ArrayList<>();
        classRooms.add(new ClassRoom("CKP", 80));
        classRooms.add(new ClassRoom("Budynek główny", 70));
        classRooms.add(new ClassRoom("Budynek główny", 77));
        classRooms.add(new ClassRoom("Budynek główny", 15));
        classRooms.add(new ClassRoom("CKP", 7));
        classRoomRepository.save(classRooms);

        gson = new Gson();
        RestAssured.port = port;
    }

    @Test
    public void shouldReturnClassRoomsList() throws Exception {
        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(gson.toJson(classRooms)))
                .when()
                .get("/classroom");
    }

    @Test
    public void shouldReturnClassRoomById() throws Exception {
        ClassRoom classRoom = classRooms.get(1);

        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(gson.toJson(classRoom)))
                .when()
                .get("/classroom/{classroomId}", classRoom.getId());
    }

    @Test
    public void shouldReturnStatusCode404() throws Exception {
        Integer notExistId = 7;
        expect()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", equalTo(String.format("Classroom id %d not found.", notExistId)))
                .when()
                .get("/classroom/{classroomId}", notExistId);
    }

    @Test
    public void shouldAddNewClassroom() throws Exception {
        ClassRoom classRoom = new ClassRoom("CKP", 5);

        expect()
                .statusCode(HttpStatus.SC_CREATED)
                .body(equalTo(""))
                .given()
                .contentType(ContentType.JSON)
                .body(classRoom)
                .when()
                .put("/classroom");

        // ClassRoom newClassroom = classRoomRepository.findOne(classRoom.getId());
        // assertThat(newClassroom).isEqualTo(classRoom);
    }

    @Test
    public void shouldRemoveClassroomById() throws Exception {
        ClassRoom classRoom = classRooms.get(2);

        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(""))
                .when()
                .delete("/classroom/{classroomId}", classRoom.getId());

        ClassRoom removeClassroom = classRoomRepository.findOne(classRoom.getId());
        assertThat(removeClassroom).isNull();

    }

    @Test
    public void shouldUpdateClassRoomById() throws Exception {
        ClassRoom classRoom = classRooms.get(0);
        classRoom.setNumber(40);

        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(""))
                .given()
                .contentType(ContentType.JSON)
                .body(classRoom)
                .when()
                .post("/classroom/{classroomId}", classRoom.getId());

        ClassRoom updateClassRoom = classRoomRepository.findOne(classRoom.getId());
        assertThat(updateClassRoom).isEqualTo(classRoom);
    }
}