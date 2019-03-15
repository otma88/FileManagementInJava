package MarioJavaZadatak;

import MarioJavaZadatak.model.Datoteka;
import MarioJavaZadatak.model.UIAkcije;
import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    static UIAkcije meni = new UIAkcije();
    public static final String ANSI_RED = "\u001B[31m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    private static Datoteka datoteka = new Datoteka();

    public static void main(String[] args) {

        boolean quit = false;
        meni.pregledAkcija();
        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    meni.pregledAkcija();
                    break;
                case 1:
                    datoteka.izlistDatoteka();
                    datoteka.pogledajDatoteku();
                    break;
                case 2:
                    String filename = datoteka.kreiranjeDatoteke();
                    datoteka.kreiranjePosiljkeUDatoteku(filename);
                    break;
                case 3:
                    datoteka.brisanjeDatoteke();
                    break;
                case 4:
                    System.out.println("Aplikacija završena...");
                    quit = true;
                    break;
                    default:
                        System.out.println(ANSI_RED + "Unesli ste pogrešnu akciju" + RESET);
                        break;
            }
        }
    }

}
