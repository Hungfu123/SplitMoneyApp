package splitter;

import model.Ausgaben;

import java.util.ArrayList;
import java.util.List;

public class AusgabenVerwaltung {
   private List<Ausgaben> ausgabenListe;

   public AusgabenVerwaltung() {
       ausgabenListe = new ArrayList<>();
   }

   // Fügt Ausgabe in die Liste hinzu
   public void addAusgaben(Ausgaben ausgaben) {
       ausgabenListe.add(ausgaben);
   }

   // Fügt
   public void addAusgabeninListe(List<Ausgaben> a){
       for(Ausgaben ausgaben: a) {
           addAusgaben(ausgaben);
       }
   }
   public List<Ausgaben> getAusgaben(){
       return ausgabenListe;
   }
}
