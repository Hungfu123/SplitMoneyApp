package splitter;

import java.io.IOException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    CSVReader reader = new CSVReader();
    AusgabenVerwaltung verwaltung = new AusgabenVerwaltung();
    List<Ausgaben> ausgaben= reader.readAusgaben("ausgaben.csv");

    verwaltung.addAusgabeninListe(ausgaben);
    List<Ausgaben> ausgabenListe = verwaltung.getAusgaben();

    System.out.println(ausgabenListe);
  }


}
