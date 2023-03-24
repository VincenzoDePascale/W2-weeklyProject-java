package org.example;


import java.time.*;


public class Data {

    public long ISBN;
    public String titolo;
    public LocalDate pubblicazione;
    public int pagine;

    public Data(long ISBN, String titolo, LocalDate pubblicazione, int pagine){
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.pubblicazione = pubblicazione;
        this.pagine = pagine;
    };



}
