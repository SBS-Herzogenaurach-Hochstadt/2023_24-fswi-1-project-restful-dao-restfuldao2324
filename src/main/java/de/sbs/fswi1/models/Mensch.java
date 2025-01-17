package de.sbs.fswi1.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public abstract class Mensch {

    protected String vorname, nachname;
    protected String geburtsdatum;

    @JsonCreator
    public Mensch(@JsonProperty("vorname") String vorname, @JsonProperty("nachname") String nachname, @JsonProperty("geburtsdatum") String geburtsdatum) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Mensch mensch = (Mensch) other;

        if (mensch.getVorname().equals(this.getVorname())
                && mensch.getNachname().equals(this.getNachname())
                && mensch.getGeburtsdatum().equals(this.getGeburtsdatum())) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vorname, nachname, geburtsdatum);
    }
}