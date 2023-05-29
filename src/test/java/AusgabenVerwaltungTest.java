import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import model.Ausgaben;
import model.AusgabenVerwaltung;
import service.CSVReader;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AusgabenVerwaltungTest {



    @DisplayName("printe die CSV Datei als Array aus")
    @Test
    void printeArray() throws  IOException{
        CSVReader reader = new CSVReader("ausgaben.csv");
        List<Ausgaben> ausgaben= reader.readAusgaben();
        AusgabenVerwaltung verwaltung = new AusgabenVerwaltung(reader);

        List<Ausgaben> ausgabenListe = verwaltung.getAusgaben();

        assertThat(ausgabenListe.size()).isEqualTo(ausgaben.size());
    }
    @DisplayName("Das erste Element ist Willy mit 320.0")
    @Test
    void printeErstesElement() throws IOException {
        CSVReader reader = new CSVReader("ausgaben.csv");
        AusgabenVerwaltung verwaltung = new AusgabenVerwaltung(reader);

        List<Ausgaben> ausgabenListe = verwaltung.getAusgaben();

        assertThat(ausgabenListe.get(0)).hasToString( "Ausgaben{ Name='Willy', Ausgabe=320.0}");
    }

    @DisplayName("Die ArrayListe ist nicht leer")
    @Test
    void pruefeListeNichtLeer() throws IOException {
        CSVReader reader = new CSVReader("ausgaben.csv");
        AusgabenVerwaltung verwaltung = new AusgabenVerwaltung(reader);

        List<Ausgaben> ausgabenListe = verwaltung.getAusgaben();
        assertThat(ausgabenListe.size()).isNotZero();

    }
    @DisplayName("Erstes Element  wurde  gelöscht")
    @Test
    void removeErstesElement() throws IOException {
        CSVReader reader = new CSVReader("ausgaben.csv");
        AusgabenVerwaltung verwaltung = new AusgabenVerwaltung(reader);
        List<Ausgaben> ausgaben= verwaltung.getAusgaben();

        verwaltung.removeAusgabeVonListe(0, ausgaben);

        assertThat(ausgaben.size()).isEqualTo(6);

//        System.out.println(ausgaben);
    }


}
