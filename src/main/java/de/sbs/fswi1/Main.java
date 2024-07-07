package de.sbs.fswi1;

import de.sbs.fswi1.models.Student;
import de.sbs.fswi1.services.DataAccessObject;

public class Main {
   
    public static void main(String[] args) {

        DataAccessObject dao = new DataAccessObject();

        Student student = new Student("Gustavo", "Gustavoson", "11.01.2000", "FSWI-2");

        dao.save(student);

        System.out.println(dao.findAll());
    }
}