package org.example;

import java.time.*;

public class Rivista extends Data{
    public Periodicita periodicita;
    public Rivista(Periodicita periodicita, long ISBN, String titolo, LocalDate pubblicazione, int pagine) {
        super(ISBN, titolo, pubblicazione, pagine);
        this.periodicita = periodicita;
    }
}
