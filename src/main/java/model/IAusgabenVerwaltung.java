package model;

import java.io.IOException;
import java.util.List;

public interface IAusgabenVerwaltung {
    void addAusgaben(Ausgaben ausgaben, List<Ausgaben> a);
    void addAusgabenInArrayListe(List<Ausgaben> a);
    void removeAusgabeVonListe(int index, List<Ausgaben> a);
    List<Ausgaben> getAusgaben() throws IOException;
}
