package start;

import model.Ausgaben;
import model.AusgabenVerwaltung;
import service.AusgabenRechner;
import service.CSVReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SchuldenAusgaben {

  private final AusgabenRechner rechner;
  private final CSVReader reader;
  public SchuldenAusgaben(AusgabenRechner rechner, CSVReader reader) {
    this.rechner = rechner;
    this.reader = reader;
  }

  public static void main(String[] args) throws IOException {
    CSVReader reader = new CSVReader();
    AusgabenVerwaltung verwaltung = new AusgabenVerwaltung(reader);
    List<Ausgaben> ausgabenList = verwaltung.getAusgaben();
    AusgabenRechner rechner = new AusgabenRechner();

    SchuldenAusgaben schuldenAusgaben = new SchuldenAusgaben(rechner, reader);
    schuldenAusgaben.start(ausgabenList);
  }

  public void start(List<Ausgaben> ausgaben) throws IOException {
    Map<String, Map<String, Double>> schuldenMap = rechner.erstelleSchuldenMap(ausgaben);
    System.out.println(schuldenMap);
  }



}
