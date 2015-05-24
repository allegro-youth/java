package pl.allegro.youth.controller;


import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.After;
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
import pl.allegro.youth.model.*;
import pl.allegro.youth.model.Class;
import pl.allegro.youth.repository.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Start.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class LessonControllerTest {

    private List<Lesson> lessons;
    private Gson gson;
    private List<Lesson> currentLessons;
    private List<Lesson> nextLessons;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;

    @Autowired
    private HourRepository hourRepository;


    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        lessons = new ArrayList<>();
        currentLessons = new ArrayList<>();
        nextLessons = new ArrayList<>();

        lessonRepository.deleteAll();
        teacherRepository.deleteAll();
        classRepository.deleteAll();
        classRoomRepository.deleteAll();
        hourRepository.deleteAll();

        Teacher teacher = new Teacher(1, "Andrzej", "Gac", "AG");
        teacherRepository.save(teacher);

        ClassRoom classRoom = new ClassRoom(1, "Budynek g��wny", 80);
        classRoomRepository.save(classRoom);

        Integer time = LocalDateTime.now().getHour() * 100 + LocalDateTime.now().getMinute();
        Hour hour = new Hour(1, (time - 20), (time + 20));
        hourRepository.save(hour);


        Class aClass = new Class(1, 2, 'k');
        classRepository.save(aClass);
        Lesson lesson = new Lesson(1, "Matematyka", "Mat", teacher, aClass, classRoom, hour);
        lessons.add(lesson);
        currentLessons.add(lesson);
        aClass = new Class(2, 3, 'h');
        classRepository.save(aClass);
        lesson = new Lesson(2, "J. Angielski", "ang", teacher, aClass, classRoom, hour);
        lessons.add(lesson);
        currentLessons.add(lesson);

        hour = new Hour(2, (time + 30), (time + 70));
        hourRepository.save(hour);
        lesson = new Lesson(3, "J. Polski", "Pol", teacher, aClass, classRoom, hour);
        lessons.add(lesson);
        nextLessons.add(lesson);


        gson = new Gson();
        RestAssured.port = port;
        lessonRepository.save(lessons);
    }

    @After
    public void tearDown() throws Exception {
        lessonRepository.deleteAll();
        teacherRepository.deleteAll();
        classRepository.deleteAll();
        classRoomRepository.deleteAll();
        hourRepository.deleteAll();
    }

    @Test
    public void shouldAddNewLesson() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .body(lessons.get(0))
                .when()
                .put("/lesson")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void shouldReturn404StatusCode() throws Exception {
        Integer notExistId = 5;
        when()
                .get("/lesson/{lessonId}", notExistId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", equalTo(String.format("Lesson id %d not found.", notExistId)));

    }

    @Test
    public void shouldReturnLessonById() throws Exception {
        expect()
                .body(equalTo(gson.toJson(lessons.get(0))))
                .when()
                .get("/lesson/{lessonId}", lessons.get(0).getId());
    }

    @Test
    public void shouldReturnLessonsList() throws Exception {
        expect()
                .body(equalTo(gson.toJson(lessons)))
                .when()
                .get("/lesson");
    }

    @Test
    public void shouldReturnCurrentLessonsList() throws Exception {
        expect()
                .body(equalTo(gson.toJson(currentLessons)))
                .when()
                .get("/lesson/current")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldReturnNextLessonsList() throws Exception {
        expect()
                .body(equalTo(gson.toJson(nextLessons)))
                .when()
                .get("/lesson/next")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }
}