package model;

public class UIAkcije {


    /**
     * Metoda za ispis početnog menija
     */
    public void pregledAkcija(){
        System.out.println("--------------------------------------------------\nPregled akcija (Home)\n---------------------" +
                "-----------------------------");
        System.out.println("0 - Pregled akcija\n" +
                "1 - Pregled postojećih datoteka\n" +
                "2 - Kreiranje datoteke\n" +
                "3 - Brisanje datoteke\n" +
                "4 - Završi aplikaciju.\n");
        System.out.println("Odaberi akciju: ");
    }

    /**
     * Metoda za ispis menija za kreiranje datoteke
     */
    public void pregledAkcijaKreiranjeDatoteke(){
        System.out.println("-------------------\nKreiranje datoteke\n-------------------");
        System.out.println("1 - Unos nove pošiljke\n" +
                "2 - Upiši pošiljke u datoteku\n" +
                "3 - Natrag");
        System.out.println("Odaberi akciju: ");

    }

    /**
     * Metoda za ispis menija za brisanje redaka u datoteci
     */
    public void pregledAkcijaBrisanjeDatoteke(){
        System.out.println("-------------------\nBrisanje redaka\n-------------------");
        System.out.println("1 - Da\n" +
                "2 - Ne\n");
        System.out.println("Odaberi akciju: ");

    }

}
