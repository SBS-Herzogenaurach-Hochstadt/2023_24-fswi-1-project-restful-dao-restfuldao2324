package de.sbs.fswi1;

import de.sbs.fswi1.services.DataAccessObject;

public class Main {
   
    public static void main(String[] args) {

        DataAccessObject dao = new DataAccessObject();
        for (var item : dao.findAll())
            System.out.println(item);
    }
}