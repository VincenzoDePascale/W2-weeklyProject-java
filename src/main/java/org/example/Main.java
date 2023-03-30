package org.example;

import java.io.*;

import org.apache.commons.io.FileUtils;

import java.util.*;
import java.time.*;
import java.util.stream.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static List<Data> archivioCompleto = new ArrayList<>();
    public static List<Libro> arrayLibri = new ArrayList<>();
    public static List<Rivista> arrayRiviste = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        Libro L1 = new Libro("autore1", "genere1", 0000000001l, "titolo1", LocalDate.of(2022, 1, 1), 1);
        Libro L2 = new Libro("autore2", "genere2", 0000000002l, "titolo2", LocalDate.of(2023, 1, 2), 2);
        Libro L3 = new Libro("autore3", "genere3", 0000000003l, "titolo3", LocalDate.of(2022, 1, 3), 3);
        Libro L4 = new Libro("autore4", "genere4", 0000000004l, "titolo4", LocalDate.of(2025, 1, 4), 4);
        Libro L5 = new Libro("autore5", "genere5", 0000000005l, "titolo5", LocalDate.of(2022, 1, 5), 5);
        addBook(L1);
        addBook(L2);
        addBook(L3);
        addBook(L4);
        addBook(L5);

        Rivista R1 = new Rivista(Periodicita.SEMESTRALE, 0000000011l, "titoloriv1", LocalDate.of(2022, 2, 1), 1);
        Rivista R2 = new Rivista(Periodicita.MENSILE, 0000000012l, "titoloriv2", LocalDate.of(2022, 2, 2), 2);
        Rivista R3 = new Rivista(Periodicita.SEMESTRALE, 0000000013l, "titoloriv3", LocalDate.of(2024, 2, 3), 3);
        Rivista R4 = new Rivista(Periodicita.SETTIMANALE, 0000000014l, "titoloriv4", LocalDate.of(2022, 2, 4), 4);
        Rivista R5 = new Rivista(Periodicita.MENSILE, 0000000015l, "titoloriv5", LocalDate.of(2022, 2, 5), 5);
        Rivista R6 = new Rivista(Periodicita.SEMESTRALE, 0000000016l, "titoloriv6", LocalDate.of(2026, 2, 6), 6);
        addRivista(R1);
        addRivista(R2);
        addRivista(R3);
        addRivista(R4);
        addRivista(R5);
        addRivista(R6);


        //rimozioneConISBN();
        //ricercaPerAnno();
        //creating();
        //ricercaPerAutore();
        scriviFile();
        leggiFile();

    }

    public static void addToArc(Data e) {
        archivioCompleto.add(e);
    }

    ;

    public static void addBook(Libro e) {
        arrayLibri.add(e);
        addToArc(e);
    }

    public static void addRivista(Rivista e) {
        arrayRiviste.add(e);
        addToArc(e);

    }

    public static void creating() {
        System.out.println("inserisci il numero di elementi che vuoi inserire");
        int n = input.nextInt();
        input.nextLine();
        System.out.println("inserisci il tipo di elementi che vuoi inserire\npremi 1 per libri 0 2 per riviste");
        int t = input.nextInt();
        input.nextLine();
        String elem = null;
        switch (t) {
            case 1:
                elem = "book";
                break;
            case 2:
                elem = "riv";
                break;
            default:
                System.out.println("inserimento non andato a buon fine, riprova:");
                creating();
        }
        for (int i = 0; i < n; i++) {
            System.out.println("inserisci ISBN:()solo numeri");
            long ISBN = input.nextLong();
            input.nextLine();
            System.out.println("inserisci titolo:");
            String titolo = input.next();
            System.out.println("inserisci numero pagine");
            LocalDate now = LocalDate.now();
            int pagine = input.nextInt();
            input.nextLine();
            if (elem.equals("book")) {
                System.out.println("inserisci autore:");
                String autore = input.next();
                System.out.println("inserisci genere:");
                String genere = input.next();
                Libro bkn = new Libro(autore, genere, ISBN, titolo, now, pagine);
                addBook(bkn);
            } else if (elem.equals("riv")) {
                System.out.println("seleziona il tipo di periodico\n1: settimanale\n2: mensile\n3: semestrale");
                int time = input.nextInt();
                input.nextLine();
                Periodicita periodo;
                switch (time) {
                    case 1:
                        periodo = Periodicita.SEMESTRALE;
                        break;
                    case 2:
                        periodo = Periodicita.MENSILE;
                        break;
                    case 3:
                        periodo = Periodicita.SEMESTRALE;
                        break;
                    default:
                        periodo = Periodicita.MENSILE;
                        break;
                }
                Rivista riv = new Rivista(periodo, ISBN, titolo, now, pagine);
                addRivista(riv);
            }
        }
    }

    ;


    public static void rimozioneConISBN() {

        System.out.println("\ninizio processo eleminazione tramite ISBN\ninserire ISBN(dieci cifre)");
        long e = input.nextLong();
        input.nextLine();

        List<Data> archivioCompletoStream = archivioCompleto.stream()
                .filter(p -> p.ISBN != e)
                .collect(Collectors.toList());

        archivioCompleto = archivioCompletoStream;

        System.out.println("questi sono i testi rimasti in archivio");
        for (int i = 0; i < archivioCompleto.size(); i++) {
            System.out.println(archivioCompleto.get(i).titolo);
        }
    }

    ;

    public static void ricercaPerAnno() {

        System.out.println("\ninizio processo ricerca per anno\ninserire anno(quattro cifre)");
        long e = input.nextLong();
        //input.nextLine();

        archivioCompleto.stream()
                .filter(x -> x.pubblicazione.getYear() >= e)
                .forEach(x -> System.out.println(x.titolo.toString()));
    }

    ;

    public static void ricercaPerAutore() {
        System.out.println("\ninizio processo ricerca libro per autore\ninserire il nome dell'autore");
        String a = input.next();

        arrayLibri.stream()
                .filter(x -> x.autore == a)
                .forEach(x -> System.out.println(x.titolo.toString()));

    }

    ;

    public static void scriviFile() throws Exception {
        File file = new File("archivioCompleto.txt");
        List<String> contenuti = new ArrayList<String>();
        try {
            for (int i = 0; i < archivioCompleto.size(); i++) {
                if (archivioCompleto.get(i) instanceof Libro) {
                    contenuti.add(archivioCompleto.get(i).ISBN + "/" + archivioCompleto.get(i).titolo + "/" + archivioCompleto.get(i).pagine + "/" + archivioCompleto.get(i).pubblicazione + "/" + ((Libro) archivioCompleto.get(i)).autore + "/" + ((Libro) archivioCompleto.get(i)).genere);
                } else if (archivioCompleto.get(i) instanceof Rivista) {
                    contenuti.add(archivioCompleto.get(i).ISBN + "/" + archivioCompleto.get(i).titolo + "/" + archivioCompleto.get(i).pagine + "/" + archivioCompleto.get(i).pubblicazione + "/" + String.valueOf(((Rivista) archivioCompleto.get(i)).periodicita));
                }
            }
            FileUtils.writeLines(file, contenuti);
        } catch (Exception e) {
            e.getStackTrace();
        }
        ;

    }

    ;

    public static void leggiFile() throws Exception {
        try {
            File file = new File("archivioCompleto.txt");
            String fileInput = FileUtils.readFileToString(file, "UTF-8");
            System.out.println(fileInput.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;


}