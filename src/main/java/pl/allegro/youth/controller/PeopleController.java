package pl.allegro.youth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.allegro.youth.model.People;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
public class PeopleController {

	@RequestMapping(value = "/people/{name}", method = RequestMethod.GET)
	public People getPeople(@RequestParam("name") String name) {
		return new People(name);
	}

	public People getPeopleCapitalizeName(String name) {
		// TODO: Spraw by zmienna name zaczynała się zawsze wielką literą
		return null;
	}

	public People getPeopleInvertedName(String name) {
		// TODO: Spraw by zmienna name została wypisana od tyłu
		return null;
	}

	public People getPeopleUppercaseName(String name) {
		return new People(name.toUpperCase());
	}

	public List<People> getPeopleFromFile() throws IOException {
		Properties prop = new Properties();
		InputStream input = null;
		List<People> peoples = new ArrayList<>();

		try {
			String filename = "config.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			prop.load(input);
			for (String name : prop.getProperty("names").split(", ")) {
				peoples.add(new People(name));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return peoples;
	}
}