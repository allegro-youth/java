package pl.allegro.youth

import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.ClassRule
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.util.MultiValueMap
import pl.allegro.youth.Start
import spock.lang.Shared
import spock.lang.Specification

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Start.class)
@IntegrationTest
@ActiveProfiles("integration")
@WebAppConfiguration
abstract class IntegrationSpecification extends Specification {

	private static final int WIREMOCK_PORT = 8099
	protected static final String LOCALHOST = "http://localhost:8080"

	@Shared
	@ClassRule
	def WireMockRule wireMockRule = new WireMockRule(WIREMOCK_PORT)
	def template = new TestRestTemplate()
	def entity

	def setEntity(HttpHeaders headers, String body) {
		entity = new HttpEntity<String>(body, headers);
	}

	def setFileEntity(HttpHeaders headers, MultiValueMap<String, Object> parts) {
		entity = new HttpEntity<MultiValueMap<String, Object>>(parts, headers)
	}

	def setEntity(HttpHeaders headers) {
		setEntity(headers, null)
	}
}
