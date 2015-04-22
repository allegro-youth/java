package pl.allegro.youth.controller;

import org.junit.Before;
import org.junit.Test;
import pl.allegro.youth.model.People;

import java.util.ArrayList;
import java.util.List;

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
		String name = "name";
		People people = controller.getPeopleUppercaseName(name);
		assertThat(people.getName()).isEqualTo("NAME");

	}

	@Test
	public void shouldReturnAllPeopleFromConfigProperties() throws Exception {
		List<People> peoples = new ArrayList<>();
		peoples.add(new People("Artur"));
		peoples.add(new People("Stefan"));
		peoples.add(new People("Wacek"));
		peoples.add(new People("Kasia"));

		List<People> peoplesMethod = controller.getPeopleFromFile();

		assertThat(peoplesMethod).containsAll(peoples);

	}
}