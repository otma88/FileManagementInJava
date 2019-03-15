package MarioJavaZadatak.enums;

public enum NacinPlacanja {

    GOTOVINA('G', "Gotovina"),
    PO_UGOVORU('U', "Po ugovoru");

    private char oznaka;
    private String label;

    NacinPlacanja(char oznaka, String label) {
        this.oznaka = oznaka;
        this.label = label;
    }

    public static String getLabelFromOznaka(Character oznaka) {
        String retval = null;
        for (NacinPlacanja nacinPlacanja : NacinPlacanja.values()) {
            if (nacinPlacanja.getOznaka() == oznaka) {
                retval = nacinPlacanja.getLabel();
            }
        }
        return  retval;
    }

    public static NacinPlacanja getInstanceFromInput(Character input) {
        NacinPlacanja retval = null;
        for (NacinPlacanja nacinPlacanja : NacinPlacanja.values()) {
            if (nacinPlacanja.getOznaka() == input) {
                retval = nacinPlacanja;
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
