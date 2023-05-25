package splitter;

import java.util.ArrayList;
import java.util.List;

public class AusgabenVerwaltung {
   private  List<Ausgaben> ausgabenListe;

   public AusgabenVerwaltung() {
       ausgabenListe = new ArrayList<>();
   }

   // Fügt Ausgabe in die Liste hinzu
   public void addAusgaben(Ausgaben ausgaben) {
       ausgabenListe.add(ausgaben);
   }

   // Fügt alle Ausgaben in die ArrayListe
   public void addAusgabenInArrayListe(List<Ausgaben> a){
       for(Ausgaben ausgaben: a) {
           addAusgaben(ausgaben);
       }
   }
   //lösche eine Ausgabe von der Array-Liste
   public void removeAusgabeVonListe(int index) {
        ausgabenListe.remove(index);
   }
   public List<Ausgaben> getAusgaben(){
       return ausgabenListe;
   }
}
