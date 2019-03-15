package enums;

public enum KategorijaPosiljke {

    HPEKSPRESS('X',"Hpekspres pošiljke"),
    EMS('E',"EMS pošiljke"),
    VRIJEDNOSNE('V', "Vrijednosne pošiljke"),
    PRACENE('Y',"Praćene pošiljke"),
    PAKETI('P',"Paketi"),
    PREPORUCENE('R',"Preporučene pošiljke"),
    OBICNE('O', "Obične pošiljke"),
    BRZOJAVI('B', "Brzojavi");

    private char oznaka;
    private String label;

    KategorijaPosiljke(char oznaka, String label) {
        this.oznaka = oznaka;
        this.label = label;
    }

    public static String getLabelFromOznaka(Character oznaka) {
        String retval = null;

        for (KategorijaPosiljke kategorija : KategorijaPosiljke.values()) {
            if (kategorija.getOznaka() == oznaka) {
                retval = kategorija.getLabel();
                break;
            }
        }
        return  retval;
    }

    public static KategorijaPosiljke getInstanceFromInput(Character input) {
        KategorijaPosiljke retval = null;
        for (KategorijaPosiljke kategorija : KategorijaPosiljke.values()) {
            if (kategorija.getOznaka() == input) {
                retval = kategorija;
            }
        }
        return retval;
    }



    public char getOznaka() {
        return oznaka;
    }

    public void setOznaka(char oznaka) {
        this.oznaka = oznaka;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
