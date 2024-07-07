package de.sbs.fswi1.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDTO extends Mensch {

	protected String klasse;
    private long id;

	@JsonCreator
	public StudentDTO(@JsonProperty("vorname") String vorname, @JsonProperty("nachname") String nachname, @JsonProperty("geburtsdatum") String geburtsdatum, @JsonProperty("klasse") String klasse) {
		super(vorname, nachname, geburtsdatum);
		this.klasse = klasse;
	}

	public String getKlasse() {
		return klasse;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "StudentDTO{" +
				"klasse='" + klasse + '\'' +
				", id=" + id +
				", vorname='" + vorname + '\'' +
				", nachname='" + nachname + '\'' +
				", geburtsdatum='" + geburtsdatum + '\'' +
				'}';
	}
}
