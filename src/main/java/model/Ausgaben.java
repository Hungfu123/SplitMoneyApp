package model;

public class Ausgaben {
    private final String NAME;
    private final Double AUSGABE;
    public Ausgaben(String NAME, Double AUSGABE) {
        this.NAME = NAME;
        this.AUSGABE = AUSGABE;
    }

    public String getNAME() {
        return NAME;
    }

    public Double getAUSGABE() {
        return AUSGABE;
    }


    @Override
    public String toString() {
        return "Ausgaben{ Name='"+ NAME +'\''+", Ausgabe="+ AUSGABE +'}';
    }
}
