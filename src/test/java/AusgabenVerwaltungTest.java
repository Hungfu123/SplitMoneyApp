import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import splitter.Ausgaben;
import splitter.AusgabenVerwaltung;
import splitter.CSVReader;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AusgabenVerwaltungTest {



    @DisplayName("printe die CSV Datei als Array aus")
    @Test
    void printeArray() throws  IOException{
        CSVReader reader = new CSVReader();
        AusgabenVerwaltung verwaltung = new AusgabenVerwaltung();
        List<Ausgaben> ausgaben= reader.readAusgaben("ausgaben.csv");

        verwaltung.addAusgabenInArrayListe(ausgaben);
        List<Ausgaben> ausgabenListe = verwaltung.getAusgaben();

        assertThat(ausgabenListe.size()).isEqualTo(ausgaben.size());
        assertThat(ausgabenListe.containsAll(ausgaben)).isTrue();
    }
    @DisplayName("Das erste Element ist Willy mit 320.0")
    @Test
    void printeErstesElement() throws IOException {
        CSVReader reader = new CSVReader();
        AusgabenVerwaltung verwaltung = new AusgabenVerwaltung();
        List<Ausgaben> ausgaben= reader.readAusgaben("ausgaben.csv");

        verwaltung.addAusgabenInArrayListe(ausgaben);
        List<Ausgaben> ausgabenListe = verwaltung.getAusgaben();

        assertThat(ausgabenListe.get(0)).hasToString( "Ausgaben{ Name='Willy', Ausgabe=320.0}");
    }

    @DisplayName("Die ArrayListe ist nicht leer")
    @Test
    void pruefeListeNichtLeer() throws IOException {
        CSVReader reader = new CSVReader();
        AusgabenVerwaltung verwaltung = new AusgabenVerwaltung();
        List<Ausgaben> ausgaben= reader.readAusgaben("ausgaben.csv");

        verwaltung.addAusgabenInArrayListe(ausgaben);
        List<Ausgaben> ausgabenListe = verwaltung.getAusgaben();
        System.out.println(ausgabenListe);
        assertThat(ausgabenListe.size()).isNotZero();

    }
    @DisplayName("Erstes Element  wurde  gelöscht")
    @Test
    void removeErstesElement() throws IOException {
        CSVReader reader = new CSVReader();
        AusgabenVerwaltung verwaltung = new AusgabenVerwaltung();
        List<Ausgaben> ausgaben= reader.readAusgaben("ausgaben.csv");
        verwaltung.addAusgabenInArrayListe(ausgaben);

        verwaltung.removeAusgabeVonListe(0);


        assertThat(ausgaben.size()-1).isEqualTo(6);


    }
}
