package MarioJavaZadatak.enums;

public enum VrstaPrometa {

    UNUTRASNJI('U', "Unutrašnji"),
    VANJSKI('V', "Vanjski");

    private char oznaka;
    private String label;

    VrstaPrometa(char oznaka, String label) {
        this.oznaka = oznaka;
        this.label = label;
    }

    public static String getLabelFromOznaka(Character oznaka) {
        String retval = null;
        for (VrstaPrometa vrstaPrometa : VrstaPrometa.values()) {
            if (vrstaPrometa.getOznaka() == oznaka){
                retval = vrstaPrometa.getLabel();
                break;
            }
        }
        return retval;
    }

    public static VrstaPrometa getInstanceFromInput(Character input) {
        VrstaPrometa retval = null;
        for (VrstaPrometa vrstaPrometa : VrstaPrometa.values()) {
            if (vrstaPrometa.getOznaka() == input) {
                retval = vrstaPrometa;
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
