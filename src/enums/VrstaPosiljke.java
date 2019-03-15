package MarioJavaZadatak.enums;

public enum VrstaPosiljke {

    PISMO('O',"Pismo"),
    DOPISNICA('C',"Dopisnica"),
    TISKANICA('T',"Tiskanica"),
    PAKET('B',"Paket"),
    MALI_PAKET('M',"Mali paket"),
    HPEKSPRES_POSILJKA('X',"Hpekspres pošiljka");

    private char oznaka;
    private String label;

    VrstaPosiljke(char oznaka, String label) {
        this.oznaka = oznaka;
        this.label = label;
    }

    public static String getLabelFromOznaka(Character oznaka) {
        String retval = null;

        for (VrstaPosiljke vrstaPosiljke : VrstaPosiljke.values()) {
            if (vrstaPosiljke.getOznaka() == oznaka) {
                retval = vrstaPosiljke.getLabel();
                break;
            }
        }
        return retval;
    }

    public static VrstaPosiljke getInstanceFromInput(Character input) {
        VrstaPosiljke retval = null;

        for (VrstaPosiljke vrsta : VrstaPosiljke.values()) {
            if (vrsta.getOznaka() == input) {
                retval = vrsta;
            }
        }
        return  retval;
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
