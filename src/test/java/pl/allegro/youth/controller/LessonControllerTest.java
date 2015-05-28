package pl.allegro.youth.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Start.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class LessonControllerTest {

    private List<Lesson> lessons;
    private Gson gson;

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
        Integer time = LocalDateTime.now().getHour() * 100 + LocalDateTime.now().getMinute();
        lessons = new ArrayList<>();

        lessonRepository.deleteAll();
        teacherRepository.deleteAll();
        classRepository.deleteAll();
        classRoomRepository.deleteAll();
        hourRepository.deleteAll();

        teacherRepository.save(new Teacher(1, "Andrzej", "Gac", "AG"));

        classRoomRepository.save(new ClassRoom(1, "CKP", 80));

        hourRepository.save(new Hour(1, (time - 20), (time + 20)));
        hourRepository.save( new Hour(2, (time + 30), (time + 70)));

        classRepository.save( new Class(1, 2, 'k'));
        classRepository.save(new Class(2, 3, 'h'));

        lessons.add(new Lesson(1, "Matematyka", "Mat", teacherRepository.findOne(1), classRepository.findOne(1), classRoomRepository.findOne(1), hourRepository.findOne(1)));
        lessons.add(new Lesson(2, "J. Angielski", "ang",teacherRepository.findOne(1), classRepository.findOne(2), classRoomRepository.findOne(1), hourRepository.findOne(1)));
        lessons.add(new Lesson(3, "J. Polski", "Pol", teacherRepository.findOne(1), classRepository.findOne(1), classRoomRepository.findOne(1), hourRepository.findOne(2)));
        lessonRepository.save(lessons);


        gson = new GsonBuilder()
                .serializeNulls()
                .create();
        RestAssured.port = port;
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
    public void shouldRemoveLessonById() throws Exception {
        Lesson lesson = lessons.get(0);
        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(""))
                .when()
                .delete("/lesson/{hourId}", lesson.getId());

        Lesson removeLesson = lessonRepository.findOne(lesson.getId());
        assertThat(removeLesson).isNull();
    }

    @Test
    public void shouldUpdateLessonById() throws Exception {
        Lesson lesson = lessonRepository.findOne(1);
        lesson.setName("Fizyka");

        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(""))
                .given()
                .contentType(ContentType.JSON)
                .body(lesson)
                .when()
                .post("/lesson/{lessonId}", lesson.getId());

/*        Lesson updateLesson = lessonRepository.findOne(lesson.getId());
        assertThat(updateLesson).isEqualTo(lesson);*/

    }

    @Test
    public void shouldReturnCurrentLessonsList() throws Exception {
        List<Lesson> currentLessons = new ArrayList<>();
        currentLessons.add(lessons.get(0));
        currentLessons.add(lessons.get(1));

        expect()
                .body(equalTo(gson.toJson(currentLessons)))
                .when()
                .get("/lesson/current")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void shouldReturnNextLessonsList() throws Exception {

        List<Lesson> nextLessons = new ArrayList<>();
        nextLessons.add(lessons.get(2));
        nextLessons.add(new Lesson(classRepository.findOne(2)));
        expect()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(equalTo(gson.toJson(nextLessons)))
                .when()
                .get("/lesson/next");

    }
}