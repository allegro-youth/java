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
import pl.allegro.youth.model.Hour;
import pl.allegro.youth.repository.HourRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Start.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class HourControllerTest {

    private List<Hour> hours;
    private Hour currentHour;
    private Gson gson;
    private Integer time;

    @Autowired
    private HourRepository hourRepository;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        time = LocalDateTime.now().getHour() * 100 + LocalDateTime.now().getMinute();

        hourRepository.deleteAll();
        hours = new ArrayList<>();
        hours.add(new Hour(1, 815, 900));
        hours.add(new Hour(2, 905, 950));
        hours.add(new Hour(3, 955, 1040));
        hours.add(new Hour(4, 1045, 1130));
        hours.add(new Hour(5, 1145, 1230));
        hours.add(new Hour(6, 1235, 1320));
        hours.add(new Hour(7, 1325, 1410));
        hours.add(new Hour(8, 1430, 1515));
        hours.add(new Hour(9, 1520, 1605));
        if(time > 1605 || time < 815) {
            hours.add(new Hour(10, (time - 20), (time + 20)));
            hours.add(new Hour(11, (time + 30), (time + 70)));
        }
        hourRepository.save(hours);


        currentHour = new Hour();
        for (Hour hour : hours){
            if(hour.getStart() < time && hour.getEnd() > time){
                currentHour = hour;
                break;
            }
        }

        gson = new Gson();
        RestAssured.port = port;
    }

    @Test
    public void shouldReturnHoursList() throws Exception {

        expect()
                .body(equalTo(gson.toJson(hours)))
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get("/hour");
    }

    @Test
    public void shouldReturnHourById() throws Exception {
        expect()
                .body(equalTo(gson.toJson(hours.get(0))))
                .statusCode(HttpStatus.SC_OK)
                .when()
                .get("/hour/{hourId}", hours.get(0).getNumber());
    }

    @Test
    public void shouldReturnStatusCode404() throws Exception {
        Integer notExistId = 12;
        expect()
                .body("message", equalTo(String.format("Hour number %d not found.", notExistId)))
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .when()
                .get("/hour/{hourId}", notExistId);

    }

    @Test
    public void shouldAddNewHour() throws Exception {

        Hour hour = new Hour(12, 1615, 1700);

        expect()
                .statusCode(HttpStatus.SC_CREATED)
                .body(equalTo(""))
                .given()
                .body(hour)
                .contentType(ContentType.JSON)
                .when()
                .put("/hour");

        Hour newHour = hourRepository.findOne(hour.getNumber());
        assertThat(newHour).isEqualTo(hour);
    }

    @Test
    public void shouldRemoveHourById() throws Exception {

        Hour hour = hours.get(0);
        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(""))
                .when()
                .delete("/hour/{hourId}", hour.getNumber());

        Hour removeHour = hourRepository.findOne(hour.getNumber());
        assertThat(removeHour).isNull();

    }

    @Test
    public void shouldReturnCurrentHour() throws Exception {
        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(gson.toJson(currentHour)))
                .when()
                .get("/hour/current");
    }

    @Test
    public void shouldReturnNextHour() throws Exception {
        Hour nextHour = hourRepository.findOne(currentHour.getNumber()+1);
        expect()
                .statusCode(HttpStatus.SC_OK)
                .body(equalTo(gson.toJson(nextHour)))
                .when()
                .get("/hour/next");

    }
}