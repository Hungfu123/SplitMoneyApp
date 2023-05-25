import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import splitter.Ausgaben;
import splitter.AusgabenRechner;
import splitter.CSVReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AusgabenRechnerTest {
    @DisplayName("Rechne die Schulden")
    @Test
    void printeArray() throws IOException {
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben("ausgaben.csv");

        AusgabenRechner rechner = new AusgabenRechner(ausgaben);
        rechner.summiereEinzelneAusgaben();
        System.out.println(rechner.summiereEinzelneAusgaben());
    }
    @DisplayName("Gesamtkosten sind 1053 Euro")
    @Test
    void getGesamtkosten() throws IOException {
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben("ausgaben.csv");
        AusgabenRechner rechner = new AusgabenRechner(ausgaben);

        Double summe = rechner.getGesamtsumme();
        assertThat(summe).isEqualTo(1053);
    }
    @DisplayName("Personenanzahl beträgt 5 in ausgabe.csv")
    @Test
    void getPersonenAnzahl() throws IOException {
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben("ausgaben.csv");
        AusgabenRechner rechner = new AusgabenRechner(ausgaben);

        long anzahl = rechner.getAnzahlPersonen();
        assertThat(anzahl).isEqualTo(5);
    }

    @DisplayName("Schulden wurden beglichen auf test2.csv")
    @Test
    void begleicheSchulden() throws IOException {
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben("test2.csv");
        AusgabenRechner rechner = new AusgabenRechner(ausgaben);

        System.out.println(rechner.erstelleSchuldenListe());
        List<String> schuldenListe = rechner.erstelleSchuldenListe();
        assertThat(schuldenListe).hasToString("[Tim schuldet Hung 12.5 Euro, Tiet schuldet Andre 12.5 Euro]");
    }

    @DisplayName("printe Schulden von ausgaben.csv")
    @Test
    void printeSchulden() throws IOException {
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben("ausgaben.csv");
        AusgabenRechner rechner = new AusgabenRechner(ausgaben);

        System.out.println(rechner.erstelleSchuldenListe());
    }

}
