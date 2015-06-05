package pl.allegro.youth.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import pl.allegro.youth.model.Teacher;
import pl.allegro.youth.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Start.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class TeacherControllerTest {

    private List<Teacher> teachers;
    private Gson gson;

    @Value("${local.server.port}")
    int port;

    @Autowired
    private TeacherRepository teacherRepository;

    @Before
    public void setUp() throws Exception {
        teacherRepository.deleteAll();

        teachers = new ArrayList<>();
        teachers.add(new Teacher("Andrzej", "Gac", "AG"));
        teachers.add(new Teacher("Renata", "Gac", "RG"));
        teacherRepository.save(teachers);


        gson = new GsonBuilder()
                .serializeNulls()
                .create();
        RestAssured.port = port;
    }

    @After
    public void tearDown() throws Exception {
        teacherRepository.deleteAll();
    }

    @Test
    public void shouldAddNewTeacher() throws Exception {
        Teacher teacher = new Teacher("Andrzej", "Gac", "AG");
        given()
                .contentType(ContentType.JSON)
                .body(teacher)
                .when()
                .put("/teacher")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void shouldReturnTeachersList() throws Exception {
        when()
                .get("/teacher")
                .as(Teacher[].class);
    }

    @Test
    public void shouldReturnTeacherById() throws Exception {
        expect()
                .body(equalTo(gson.toJson(teachers.get(1))))
                .when()
                .get("/teacher/{teacherId}", teachers.get(1).getId());
    }

    @Test
    public void shouldReturn404StatusCode() throws Exception {
        Integer notExistId = 5;

        when()
                .get("/teacher/{teacherId}", notExistId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", equalTo(String.format("Teacher id %d not found", notExistId)));

    }

    @Test
    public void shouldRemoveTeacherById() throws Exception {
        when()
                .delete("/teacher/{teacherId}", teachers.get(1).getId())
                .then()
                .statusCode(HttpStatus.SC_OK);

        Teacher teacher = teacherRepository.findOne(teachers.get(1).getId());
        assertThat(teacher).isNull();
    }


    @Test
    public void shouldUpdateTeacherById() throws Exception {
        Teacher teacher = teachers.get(0);
        teacher.setFirstName("Tomasz");

        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(""))
                .given()
                .contentType(ContentType.JSON)
                .body(teacher)
                .when()
                .post("/teacher/{teacherId}", teacher.getId());

        Teacher updateTeacher = teacherRepository.findOne(teacher.getId());
        assertThat(updateTeacher).isEqualTo(teacher);

    }
}