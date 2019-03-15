package model;

import KategorijaPosiljke;
import enums.NacinPlacanja;
import enums.VrstaPosiljke;
import enums.VrstaPrometa;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Datoteka {

    private Scanner scanner = new Scanner(System.in);
    private static final String ANSI_RED = "\u001B[31m";
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[0;32m";
    static UIAkcije meni = new UIAkcije();
    private Map<Integer, Posiljka> posiljkeUDatoteci;

    public Datoteka(){
        this.posiljkeUDatoteci = new HashMap<>();
    }

    public Datoteka(Scanner scanner) {
        this.posiljkeUDatoteci = new HashMap<>();
        this.scanner = scanner;
    }

    /**
     * Prikaz svih datoteka u folderu
     */
    public void izlistDatoteka() {
        File folder = new File("posiljke");
        File[] listaDatoteka = folder.listFiles();
        if (folder.list() != null) {
            if (folder.list().length > 0) {
                System.out.println("Lista datoteka\n----------------");
                for (int i = 0; i < listaDatoteka.length; i++) {
                    if (listaDatoteka[i].isFile()) {
                        System.out.println(listaDatoteka[i].getName());
                    }
                }
            }
        } else {
            System.out.println(ANSI_RED + "Nema datoteka za prikaz!" + RESET);
        }
    }

    /**
     * Prikaz sadržaja u datoteci
     */
    public void pogledajDatoteku() {
        System.out.println("Unesite ime datoteke za prikaz: ");
        String imeDatoteke = scanner.nextLine();
        String imaDatoteke = findDatoteku(imeDatoteke);
        if (imaDatoteke != null){
            prikazDatoteke("posiljke/" + imeDatoteke);
        } else {
            System.out.println("----------------\n" + ANSI_RED + "Datoteka " + imeDatoteke + " ne postoji" + RESET);
            meni.pregledAkcija();
        }
    }

    /**
     * Metoda za pretragu datoteke
     * @param imeDatoteke
     * @return
     */
    public String findDatoteku(String imeDatoteke) {
        String retval = null;
        File folder = new File("posiljke");
        File[] listaDatoteka = folder.listFiles();
        for (int i = 0; i < listaDatoteka.length; i++) {
            if (listaDatoteka[i].getName().equals(imeDatoteke)) {
                retval = imeDatoteke;
            }
        }
        return retval;
    }

    /**
     * Metoda kreira novu praznu datoteku i vraća ime kreirane datoteke
     * @return
     */
    public String kreiranjeDatoteke() {
        File folder = new File("posiljke");
        File[] listaDatoteka = folder.listFiles();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        int num = 1;
        String filename = "posiljke/epk_" + date + "_" + num + ".csv";
        String[] filenameDate = filename.split("_");
        String filenamePart2 = filenameDate[1];
        if (listaDatoteka != null) {
            for (int i = 0; i < listaDatoteka.length; i++) {
                String[] listaDate = listaDatoteka[i].getName().split("_");
                String listaDatePart2 = listaDate[1];
                if (filenamePart2.equals(listaDatePart2)) {
                    num++;
                }
            }
            filename = folder + "/epk_" + date + "_" + num + ".csv";
        }
        this.posiljkeUDatoteci.clear();
        return filename;
    }

    /**
     * Metoda s meni-om za kreiranje posiljaka i upis posiljka u datoteku
     * @param filename
     */
    public void kreiranjePosiljkeUDatoteku(String filename) {
        meni.pregledAkcijaKreiranjeDatoteke();
        boolean quitKreiranjeDatoteke = false;
        while (!quitKreiranjeDatoteke) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 1:
                    unosNovePosiljke();
                    meni.pregledAkcijaKreiranjeDatoteke();
                    break;
                case 2:
                    writeDatoteku(filename);
                    System.out.println(GREEN + "Datoteka " + filename + " je kreirana" + RESET);
                    meni.pregledAkcijaKreiranjeDatoteke();
                    break;
                case 3:
                    meni.pregledAkcija();
                    quitKreiranjeDatoteke = true;
                    break;
                    default:
                        System.out.println(ANSI_RED + "Unesli ste pogrešnu akciju" + RESET);
                        meni.pregledAkcijaKreiranjeDatoteke();
                        break;
            }
        }
    }

    /**
     * Metoda za kreiranje nove posiljke
     */
    public void unosNovePosiljke() {
        Posiljka posiljka = new Posiljka();
        System.out.println("Unesite kategoriju (X/E/V/Y/P/R/O/B): ");
        Character kategorija = scanner.next(".").toUpperCase().charAt(0);
        while (kategorija != 'X' && kategorija != 'E' && kategorija != 'V' && kategorija != 'Y' && kategorija != 'P' &&
                kategorija != 'R' && kategorija != 'O' && kategorija != 'B' ) {
            System.out.println("Unesite ispravnu kategoriju (X/E/V/Y/P/R/O/B): ");
            kategorija = scanner.next(".").toUpperCase().charAt(0);
        }
        scanner.nextLine();
        System.out.println("Unesite prijamni broj pošiljke: ");
        String prijamniBroj = scanner.nextLine();
        while (posiljka.validateParseNumber(prijamniBroj) == false) {
            prijamniBroj = scanner.nextLine();
        }
        System.out.println("Unesite vrstu pošiljke (O/C/T/B/M/X): ");
        Character vrstaPosiljke = scanner.next(".").toUpperCase().charAt(0);
        while (vrstaPosiljke != 'O' && vrstaPosiljke != 'C' && vrstaPosiljke != 'T' && vrstaPosiljke != 'B'
                && vrstaPosiljke != 'M' && vrstaPosiljke != 'X') {
            System.out.println("Unesite ispravnu vrstu pošiljke (O/C/T/B/M/X): ");
            vrstaPosiljke = scanner.next(".").toUpperCase().charAt(0);
        }
        System.out.println("Unesite masu (grami): ");
        int masa = scanner.nextInt();
        while (masa <= 0 || masa > 5000) {
            System.out.println("Masa ne smije biti veća od 5000g. Unesite masu ponovno: ");
            masa = scanner.nextInt();
        }
        System.out.println("Unesite vrstu prometa (U/V): ");
        Character vrstaPrometa = scanner.next(".").toUpperCase().charAt(0);
        while (vrstaPrometa != 'U' && vrstaPrometa != 'V') {
            System.out.println("Unesite ispravnu vrstu prometa (U/V): ");
            vrstaPrometa = scanner.next(".").toUpperCase().charAt(0);
        }
        System.out.println("Unesite način plaćanja (G/U): ");
        Character nacinPlacanja = scanner.next(".").toUpperCase().charAt(0);
        while (nacinPlacanja != 'G' && nacinPlacanja != 'U') {
            System.out.println("Unesite ispravan način plaćanja (G/U): ");
            nacinPlacanja = scanner.next(".").toUpperCase().charAt(0);
        }
        scanner.nextLine();
        System.out.println("Unesite ime i prezime pošiljatelja: ");
        String imePrezimePosiljatelja = scanner.nextLine();
        System.out.println("Unesite telefon pošiljatelja: ");
        String telefonPosiljatelja = scanner.nextLine();
        while (!telefonPosiljatelja.matches("[0-9\\-\\/]+")) {
            System.out.println("Telefon mora sadržavati samo brojeve i znakove '-','/'. Molimo unesite ponovo: ");
            telefonPosiljatelja = scanner.nextLine();
        }
        System.out.println("Unesite adresu pošiljatelja: ");
        String adresaPosiljatelja = scanner.nextLine();
        System.out.println("Unesite ime i prezime primatelja: ");
        String imePrezimePrimatelja = scanner.nextLine();
        System.out.println("Unesite telefon primatelja: ");
        String telefonPrimatelja = scanner.nextLine();
        while (!telefonPrimatelja.matches("[0-9\\-\\/]+")) {
            System.out.println("Telefon mora sadržavati samo brojeve i znakove '-','/'. Molimo unesite ponovo: ");
            telefonPrimatelja = scanner.nextLine();
        }
        System.out.println("Unesite adresu primatelja: ");
        String adresaPrimatelja = scanner.nextLine();

        posiljka.setKategorijaPosiljke(KategorijaPosiljke.getInstanceFromInput(kategorija));
        posiljka.setPrijamniBroj(prijamniBroj);
        posiljka.setVrstaPosiljke(VrstaPosiljke.getInstanceFromInput(vrstaPosiljke));
        posiljka.setMasa(masa);
        posiljka.setVrstaPrometa(VrstaPrometa.getInstanceFromInput(vrstaPrometa));
        posiljka.setNacinPlacanja(NacinPlacanja.getInstanceFromInput(nacinPlacanja));
        posiljka.setImePrezimePosiljatelja(imePrezimePosiljatelja);
        posiljka.setTelefonPosiljatelja(telefonPosiljatelja);
        posiljka.setAdresaPosiljatelja(adresaPosiljatelja);
        posiljka.setImePrezimePrimatelja(imePrezimePrimatelja);
        posiljka.setTelefonPrimatelja(telefonPrimatelja);
        posiljka.setAdresaPrimatelja(adresaPrimatelja);


        this.posiljkeUDatoteci.put(posiljkeUDatoteci.keySet().size()+1, posiljka);

        System.out.println("------\n" + GREEN + "Pošiljka je kreirana" + RESET + "\n------");

    }

    /**
     * Metoda s meni-om za prikaz i brisanje posiljaka u datoteci
     * @param imeDatoteke
     */
    public void prikazDatoteke(String imeDatoteke) {
        readDatoteku(imeDatoteke);
        meni.pregledAkcijaBrisanjeDatoteke();
        boolean quitBrisanjeRedaka = false;
        while (!quitBrisanjeRedaka) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 1:
                    brisiRedak();
                    writeDatoteku(imeDatoteke);
                    readDatoteku(imeDatoteke);
                    meni.pregledAkcijaBrisanjeDatoteke();
                    break;
                case 2:
                    meni.pregledAkcija();
                    quitBrisanjeRedaka = true;
                    break;
                default:
                    System.out.println(ANSI_RED + "Unesli ste pogrešnu akciju" + RESET);
                    meni.pregledAkcijaBrisanjeDatoteke();
                    break;
            }
        }
    }

    /**
     * Čitanje datoteke
     * @param imeDatoteke
     */

    public void readDatoteku(String imeDatoteke) {
        System.out.println("Prikaz datoteke -> " + imeDatoteke + "\n----------------------------------------------");
        posiljkeUDatoteci.clear();
        int num = 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader(imeDatoteke));
            String line;
            String[] values;
            while ((line = br.readLine()) != null) {
                values = line.split(" \\| ");
                char kategorijaPosiljke = values[0].charAt(0);
                String prijamniBroj = values[1];
                char vrstaPosiljke = values[2].charAt(0);
                String masa = values[3];
                char vrstaPrometa = values[4].charAt(0);
                char nacinPlacanja = values[5].charAt(0);
                String imePrezimePosiljatelja = values[6];
                String telefonPosiljatelja = values[7];
                String adresaPosiljatelja = values[8];
                String imePrezimePrimatelja = values[9];
                String telefonPrimatelja = values[10];
                String adresaPrimatelja = values[11];
                System.out.println(num + "." + " | " + KategorijaPosiljke.getLabelFromOznaka(kategorijaPosiljke) + " | "
                        + prijamniBroj + " | "
                        + VrstaPosiljke.getLabelFromOznaka(vrstaPosiljke) + " | "
                        + masa + " | "
                        + VrstaPrometa.getLabelFromOznaka(vrstaPrometa) + " | "
                        + NacinPlacanja.getLabelFromOznaka(nacinPlacanja) + " | "
                        + imePrezimePosiljatelja + " | "
                        + telefonPosiljatelja + " | "
                        + adresaPosiljatelja + " | "
                        + imePrezimePrimatelja + " | "
                        + telefonPrimatelja + " | "
                        + adresaPrimatelja);
                posiljkeUDatoteci.put(num, new Posiljka(KategorijaPosiljke.getInstanceFromInput(kategorijaPosiljke)
                        , prijamniBroj
                        , VrstaPosiljke.getInstanceFromInput(vrstaPosiljke)
                        , Integer.parseInt(masa)
                        , VrstaPrometa.getInstanceFromInput(vrstaPrometa)
                        , NacinPlacanja.getInstanceFromInput(nacinPlacanja)
                        , imePrezimePosiljatelja
                        , telefonPosiljatelja
                        , adresaPosiljatelja
                        , imePrezimePrimatelja
                        , telefonPrimatelja
                        , adresaPrimatelja));
                num++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda za brisanje datoteke u folderu
     */
    public void brisanjeDatoteke() {
        izlistDatoteka();
        System.out.println("Unesite ime datoteke za brisanje: ");
        String imeDatoteke = scanner.nextLine();
        String imaDatoteke = findDatoteku(imeDatoteke);
        if (imaDatoteke != null){
            brisiDatoteku(imeDatoteke);
            System.out.println(GREEN + "Datoteka je uspješno obrisana..." + RESET);
            meni.pregledAkcija();
        } else {
            System.out.println("----------------\n" + ANSI_RED + "Datoteka " + imeDatoteke + " ne postoji" + RESET);
            meni.pregledAkcija();
        }
    }

    /**
     * Metoda za brisanje datoteke s parametrom imena datoteke za brisanje
     * @param imeDatoteke
     */
    public void brisiDatoteku(String imeDatoteke) {
        File folder = new File("posiljke");
        File[] listaDatoteka = folder.listFiles();
        for (int i = 0; i < listaDatoteka.length; i++) {
            if (listaDatoteka[i].isFile()) {
                if (listaDatoteka[i].getName().equals(imeDatoteke)) {
                    listaDatoteka[i].delete();
                }
            }
        }
    }

    /**
     * Upis posiljka u datoteku
     * @param imeDatoteke
     */
    public void writeDatoteku(String imeDatoteke) {
        try {
            FileWriter file = new FileWriter(imeDatoteke);
            for (Posiljka posiljUDatoteci : posiljkeUDatoteci.values()) {
                file.write(posiljUDatoteci.getKategorijaPosiljke().getOznaka() + " | "
                        + posiljUDatoteci.getPrijamniBroj() + " | "
                        + posiljUDatoteci.getVrstaPosiljke().getOznaka() + " | "
                        + posiljUDatoteci.getMasa() + " | "
                        + posiljUDatoteci.getVrstaPrometa().getOznaka() + " | "
                        + posiljUDatoteci.getNacinPlacanja().getOznaka() + " | "
                        + posiljUDatoteci.getImePrezimePosiljatelja() + " | "
                        + posiljUDatoteci.getTelefonPosiljatelja() + " | "
                        + posiljUDatoteci.getAdresaPosiljatelja() + " | "
                        + posiljUDatoteci.getImePrezimePrimatelja() + " | "
                        + posiljUDatoteci.getTelefonPrimatelja() + " | "
                        + posiljUDatoteci.getAdresaPrimatelja() + "\n");
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Greška kod kreiranja datoteke");
            e.printStackTrace();
        }
    }


    /**
     * Metoda za brisanje redka iz datoteke
     */
    public void brisiRedak() {
        System.out.println("Odaberi redak za brisanje: ");
        int redak = scanner.nextInt();
        posiljkeUDatoteci.remove(redak);
    }

    public Map<Integer, Posiljka> getPosiljkeUDatoteci() {
        return posiljkeUDatoteci;
    }

    public void setPosiljkeUDatoteci(Map<Integer, Posiljka> posiljkeUDatoteci) {
        this.posiljkeUDatoteci = posiljkeUDatoteci;
    }

}
