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
import pl.allegro.youth.model.Class;
import pl.allegro.youth.repository.ClassRepository;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.restassured.RestAssured.expect;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Start.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ClassControllerTest {

    private List<Class> classes;
    private Gson gson;

    @Autowired
    private ClassRepository classRepository;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        classRepository.deleteAll();
        classes = new ArrayList<>();
        classes.add(new Class(1, 1, 'b'));
        classes.add(new Class(2, 1, 'd'));
        classes.add(new Class(3, 1, 'e'));
        classes.add(new Class(4, 1, 'h'));
        classes.add(new Class(5, 1, 'k'));
        classes.add(new Class(6, 1, 't'));
        classRepository.save(classes);

        gson = new Gson();
        RestAssured.port = port;
    }

    @Test
    public void shouldReturnClassesList() throws Exception {
        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(gson.toJson(classes)))
                .when()
                .get("/class");
    }

    @Test
    public void shouldReturnClassById() throws Exception {
        Class aClass = classes.get(1);

        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(gson.toJson(aClass)))
                .when()
                .get("/class/{classId}", aClass.getId());
    }

    @Test
    public void shouldReturnStatusCode404() throws Exception {
        Integer notExistId = 8;

        expect()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("message", equalTo(String.format("Class id %d not found.", notExistId)))
                .when()
                .get("/class/{classId}", notExistId);

    }

    @Test
    public void shouldAddNewClass() throws Exception {
        Class aClass = new Class(7, 2, 'k');

        expect()
                .statusCode(HttpStatus.SC_CREATED)
                .body(equalTo(""))
                .given()
                .contentType(ContentType.JSON)
                .body(aClass)
                .when()
                .put("/class");

        Class newClass = classRepository.findOne(aClass.getId());

        assertThat(newClass).isEqualTo(aClass);
    }

    @Test
    public void shouldRemoveClassById() throws Exception {
        Class aClass = classes.get(1);

        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(""))
                .when()
                .delete("/class/{classId}", aClass.getId());

        Class removeClass = classRepository.findOne(aClass.getId());

        assertThat(removeClass).isNull();


    }
}