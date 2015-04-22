package pl.allegro.youth.controller;

import org.junit.Before;
import org.junit.Test;
import pl.allegro.youth.model.People;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class PeopleControllerTest {

	private PeopleController controller;

	@Before
	public void setUp() throws Exception {
		controller = new PeopleController();
	}

	@Test
	public void shouldReturnWrapperNameIntoPeopleObject() throws Exception {
		// given:
		String name = "name";

		// when:
		People people = controller.getPeople(name);

		// than:
		assertThat(people.getName()).isEqualTo(name);
	}

	@Test
	public void shouldReturnCapitalizeName() throws Exception {
		// given:
		String name = "name";

		// when:
		People people = controller.getPeopleCapitalizeName(name);

		// than:
		assertThat(people.getName()).isEqualTo("Name");
	}

	@Test
	public void shouldReturnInvertedName() throws Exception {
		String name = "name";

		// when:
		People people = controller.getPeopleInvertedName(name);

		// than:
		assertThat(people.getName()).isEqualTo("eman");
	}

	@Test
	public void shouldReturnUppercaseName() throws Exception {
		// TODO: Napisz test, który potwierdzi, że metoda getPeopleUppercaseName zamieni
		// imię napisane małymi literami na wielkie
		fail("Not implemented yet");
	}

	@Test
	public void shouldReturnAllPeopleFromConfigProperties() throws Exception {
		// TODO: Napisz test, który udowodni, że metoda getPeopleFromFile zwraca listę ludzi. Oraz zawiera wszystkie
		// cztery imiona: Artur, Stefan, Wacek, Kasia
		fail("Not implemented yet");
	}
}