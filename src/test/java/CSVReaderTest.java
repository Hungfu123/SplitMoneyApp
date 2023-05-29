import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import model.Ausgaben;
import service.CSVReader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CSVReaderTest {

    @DisplayName("lese ausgaben.csv und die Größe beträgt 6")
    @Test
    void leseAusgabenAusDatei() throws  Exception{
        CSVReader reader = new CSVReader("ausgaben.csv");
        List<Ausgaben> ausgaben= reader.readAusgaben();
        assertThat(ausgaben).hasSize(7);
    }
}
