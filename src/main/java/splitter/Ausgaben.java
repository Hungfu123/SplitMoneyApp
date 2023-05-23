package splitter;

public class Ausgaben {
    private final String name;
    private final Double ausgabe;
    public Ausgaben(String name, Double ausgabe) {
        this.name = name;
        this.ausgabe = ausgabe;
    }

    public String getName() {
        return name;
    }

    public Double getAusgabe() {
        return ausgabe;
    }


    @Override
    public String toString() {
        return "Ausgaben{ Name='"+name+'\''+", Ausgabe="+ ausgabe +'}';
    }
}
