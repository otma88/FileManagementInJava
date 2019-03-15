package model;

import enums.KategorijaPosiljke;
import enums.NacinPlacanja;
import enums.VrstaPosiljke;
import enums.VrstaPrometa;

import java.util.Scanner;

public class Posiljka {

    private static Scanner scanner = new Scanner(System.in);


    private KategorijaPosiljke kategorijaPosiljke;
    private String prijamniBroj;
    private VrstaPosiljke vrstaPosiljke;
    private int masa;
    private VrstaPrometa vrstaPrometa;
    private NacinPlacanja nacinPlacanja;
    private String imePrezimePosiljatelja;
    private String telefonPosiljatelja;
    private String adresaPosiljatelja;
    private String imePrezimePrimatelja;
    private String telefonPrimatelja;
    private String adresaPrimatelja;

    public Posiljka(KategorijaPosiljke kategorijaPosiljke, String prijamniBroj, VrstaPosiljke vrstaPosiljke, int masa, VrstaPrometa vrstaPrometa, NacinPlacanja nacinPlacanja, String imePrezimePosiljatelja, String telefonPosiljatelja, String adresaPosiljatelja, String imePrezimePrimatelja, String telefonPrimatelja, String adresaPrimatelja) {
        this.kategorijaPosiljke = kategorijaPosiljke;
        this.prijamniBroj = prijamniBroj;
        this.vrstaPosiljke = vrstaPosiljke;
        this.masa = masa;
        this.vrstaPrometa = vrstaPrometa;
        this.nacinPlacanja = nacinPlacanja;
        this.imePrezimePosiljatelja = imePrezimePosiljatelja;
        this.telefonPosiljatelja = telefonPosiljatelja;
        this.adresaPosiljatelja = adresaPosiljatelja;
        this.imePrezimePrimatelja = imePrezimePrimatelja;
        this.telefonPrimatelja = telefonPrimatelja;
        this.adresaPrimatelja = adresaPrimatelja;
    }

    public Posiljka() {
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";



    /**
     * Validacija prijamnog broja
     * @param prijamniBroj
     * @return
     */
    public boolean validateParseNumber(String prijamniBroj) {
        if (prijamniBroj.length() != 13) {
            System.out.println(ANSI_RED + "Prijamni broj mora imati točno 13 znakova. Molimo unesite ponovno: " + RESET);
            return false;
        } else if (!prijamniBroj.substring(0,2).equals("CP") && !prijamniBroj.substring(0,2).equals("CC")
                   && !prijamniBroj.substring(0,2).equals("LE")) {
            System.out.println(ANSI_RED + "Prve dvije znamenke prijamnog broja moraju biti 'CP', 'CC', 'LE'. Molimo unesite ponovno: " + RESET);
            return false;
        } else if (!prijamniBroj.substring(2,11).matches("[0-9]+")) {
            System.out.println(ANSI_RED + "Znamenke od 3 do 11 moraju biti brojevi. Molimo unesite ponovno" + RESET);
            return false;
        }
        int kontrolnaZnamenka = Character.getNumericValue(prijamniBroj.charAt(10));
        if (algoritamZaProvjeruBroja(prijamniBroj) != kontrolnaZnamenka) {
            System.out.println(ANSI_RED + "Prijamni broj je neispravan. Molimo unesite ponovno: " + RESET);
            return false;
        } else if (!prijamniBroj.substring(11,13).equals("HR")) {
            System.out.println(ANSI_RED + "Zadnje dvije znamenke prijamnog broja moraju biti 'HR'. Molimo unesite ponovno: " + RESET);
            return false;
        }

        return true;
    }

    /**
     * Provjera kontrolne znamenke
     * @param prijamniBroj
     * @return
     */

    private static int algoritamZaProvjeruBroja(String prijamniBroj){
        int treca = Character.getNumericValue(prijamniBroj.charAt(2));
        int cetvrta = Character.getNumericValue(prijamniBroj.charAt(3));
        int peta = Character.getNumericValue(prijamniBroj.charAt(4));
        int sesta = Character.getNumericValue(prijamniBroj.charAt(5));
        int sedma = Character.getNumericValue(prijamniBroj.charAt(6));
        int osma = Character.getNumericValue(prijamniBroj.charAt(7));
        int deveta = Character.getNumericValue(prijamniBroj.charAt(8));
        int deseta = Character.getNumericValue(prijamniBroj.charAt(9));
        int c;

        int suma = (treca*8) + (cetvrta*6) + (peta*4) + (sesta*2) + (sedma*3) + (osma*5) + (deveta*9) + (deseta*7);
        c = 11 - (suma % 11);
        if (c == 10) {
            c = 0;
            return c;
        } else if (c == 11) {
            c = 5;
            return c;
        }
        return c;
    }



    public KategorijaPosiljke getKategorijaPosiljke() {
        return kategorijaPosiljke;
    }

    public void setKategorijaPosiljke(KategorijaPosiljke kategorijaPosiljke) {
        this.kategorijaPosiljke = kategorijaPosiljke;
    }

    public VrstaPosiljke getVrstaPosiljke() {
        return vrstaPosiljke;
    }

    public void setVrstaPosiljke(VrstaPosiljke vrstaPosiljke) {
        this.vrstaPosiljke = vrstaPosiljke;
    }

    public int getMasa() {
        return masa;
    }

    public void setMasa(int masa) {
        this.masa = masa;
    }

    public VrstaPrometa getVrstaPrometa() {
        return vrstaPrometa;
    }

    public void setVrstaPrometa(VrstaPrometa vrstaPrometa) {
        this.vrstaPrometa = vrstaPrometa;
    }

    public NacinPlacanja getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(NacinPlacanja nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public String getImePrezimePosiljatelja() {
        return imePrezimePosiljatelja;
    }

    public void setImePrezimePosiljatelja(String imePrezimePosiljatelja) {
        this.imePrezimePosiljatelja = imePrezimePosiljatelja;
    }

    public String getTelefonPosiljatelja() {
        return telefonPosiljatelja;
    }

    public void setTelefonPosiljatelja(String telefonPosiljatelja) {
        this.telefonPosiljatelja = telefonPosiljatelja;
    }

    public String getAdresaPosiljatelja() {
        return adresaPosiljatelja;
    }

    public void setAdresaPosiljatelja(String adresaPosiljatelja) {
        this.adresaPosiljatelja = adresaPosiljatelja;
    }

    public String getImePrezimePrimatelja() {
        return imePrezimePrimatelja;
    }

    public String getPrijamniBroj() {
        return prijamniBroj;
    }

    public void setPrijamniBroj(String prijamniBroj) {
        this.prijamniBroj = prijamniBroj;
    }

    public void setImePrezimePrimatelja(String imePrezimePrimatelja) {
        this.imePrezimePrimatelja = imePrezimePrimatelja;
    }

    public String getTelefonPrimatelja() {
        return telefonPrimatelja;
    }

    public void setTelefonPrimatelja(String telefonPrimatelja) {
        this.telefonPrimatelja = telefonPrimatelja;
    }

    public String getAdresaPrimatelja() {
        return adresaPrimatelja;
    }

    public void setAdresaPrimatelja(String adresaPrimatelja) {
        this.adresaPrimatelja = adresaPrimatelja;
    }
}
