import model.AusgabenVerwaltung;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import model.Ausgaben;
import service.AusgabenRechner;
import service.CSVReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AusgabenRechnerTest {

    @DisplayName("Gesamtkosten sind 1053 Euro")
    @Test
    void getGesamtkosten() throws IOException {
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben();
        AusgabenRechner rechner = new AusgabenRechner();

        Double summe = rechner.getGesamtsumme(ausgaben);
        assertThat(summe).isEqualTo(1053);
    }
    @DisplayName("Personenanzahl beträgt 5 in ausgabe.csv")
    @Test
    void getPersonenAnzahl() throws IOException {
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben();
        AusgabenRechner rechner = new AusgabenRechner();

        long anzahl = rechner.getAnzahlPersonen(ausgaben);
        assertThat(anzahl).isEqualTo(5);
    }

    @DisplayName("Schulden wurden beglichen auf test2.csv")
    @Test
    void begleicheSchulden() throws IOException {
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben();
        AusgabenRechner rechner = new AusgabenRechner();

        Map<String, Map<String, Double>> schuldenMap = rechner.erstelleSchuldenMap(ausgaben);

        assertThat(schuldenMap).hasToString("{Hung={Tim=12.5}, Andre={Tiet=12.5}}");
    }

    @DisplayName("printe Schulden von test.csv")
    @Test
    void printeSchulden() throws IOException {
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben();
        AusgabenRechner rechner = new AusgabenRechner();

        Map<String, Map<String, Double>> schuldenMap = rechner.erstelleSchuldenMap(ausgaben);

        assertThat(schuldenMap).hasToString("{Willy={Karl=127.0, Gaby=66.0}, Tim={Gaby=13.0}}");

    }

    @DisplayName("printe Schulden von ausgaben.csv")
    @Test
    void printeSchuldenAusgabe() throws IOException {
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben();
        AusgabenRechner rechner = new AusgabenRechner();
        Map<String, Map<String, Double>> schuldenMap = rechner.erstelleSchuldenMap(ausgaben);

        assertThat(schuldenMap).hasToString("{Hung={Tiet=165.6, Gaby=123.79999999999998}, Willy={Gaby=38.80000000000001, Tim=70.6}}");
    }

    @DisplayName("Erstes Element wurde aus der Liste entfernt und rechnet die Schulden. Kein Willy")
    @Test
    void rechneTest () throws IOException {
        CSVReader reader = new CSVReader();
        AusgabenVerwaltung verwaltung = new AusgabenVerwaltung(reader);

        List<Ausgaben> ausgaben= reader.readAusgaben();
        verwaltung.removeAusgabeVonListe(0, ausgaben);
        AusgabenRechner rechner = new AusgabenRechner();

        Map<String, Map<String, Double>> schuldenMap = rechner.erstelleSchuldenMap(ausgaben);
        assertThat(schuldenMap).hasToString("{Hung={Tiet=138.25, Gaby=135.25, Tim=43.25}}");
    }

}
