package model;

import service.ICSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AusgabenVerwaltung implements IAusgabenVerwaltung{

   private ICSVReader reader;
   public AusgabenVerwaltung(ICSVReader reader) {
       this.reader = reader;

   }

   // Fügt Ausgabe in die Liste hinzu
   public void addAusgaben(Ausgaben ausgaben, List<Ausgaben> a) {
       a.add(ausgaben);
   }

//    Fügt alle Ausgaben in die ArrayListe
   public void addAusgabenInArrayListe(List<Ausgaben> a){
       for(Ausgaben ausgaben: a) {
           addAusgaben(ausgaben, a);
       }
   }
   //lösche eine Ausgabe von der Array-Liste
   public void removeAusgabeVonListe(int index, List<Ausgaben> a) {
        a.remove(index);
   }
   public List<Ausgaben> getAusgaben() throws IOException {
       return reader.readAusgaben();
   }
}
