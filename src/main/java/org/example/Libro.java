package org.example;

import java.time.*;
import java.util.*;


public class Libro extends Data {
    public String autore;
    public String genere;

    public Libro(String autore, String genere, long ISBN, String titolo, LocalDate pubblicazione, int pagine) {
        super(ISBN, titolo, pubblicazione, pagine);
        this.autore = autore;
        this.genere = genere;

    }



}
