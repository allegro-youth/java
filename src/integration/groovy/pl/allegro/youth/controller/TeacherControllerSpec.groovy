package pl.allegro.youth.controller

import org.mockito.Mockito
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.util.MultiValueMap
import pl.allegro.youth.IntegrationSpecification
import pl.allegro.youth.model.Teacher
import pl.allegro.youth.repository.TeacherRepository

import javax.ws.rs.core.Response

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

class TeacherControllerSpec extends IntegrationSpecification {

    private static final String TEACHER_ID = "5596322056906d896addbaa1";

    def mock = Mockito.mock(TeacherRepository.class)

    def "should return teacher list"() {
        given:
        stubFor(get(
                urlEqualTo("/teachers")
        ).willReturn(aResponse()
                .withBody()
                .withStatus(Response.Status.OK.statusCode))
        )
    }


    def "should return teacher by id"() {
        given:
        stubFor(get(
                urlEqualTo("/teachers/" + TEACHER_ID)
        ).willReturn(aResponse()
                .withStatus(Response.Status.OK.statusCode))
        )

        when:
        def result = template.exchange("http://localhost:8080/teachers/" + TEACHER_ID, HttpMethod.GET, entity, Teacher)

        then:
        result.statusCode.value() == Response.Status.OK.statusCode
    }


}
