package pl.allegro.youth.model;

import java.util.Objects;

public class People {

	private String name;

	public People(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final People other = (People) obj;
		return Objects.equals(this.name, other.name);
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				'}';
	}
}
