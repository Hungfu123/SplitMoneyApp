import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import splitter.Ausgaben;
import splitter.CSVReader;
import org.assertj.core.api.Assertions;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CSVReaderTest {

    @DisplayName("lese ausgaben.csv und die Größe beträgt 6")
    @Test
    void leseAusgabenAusDatei() throws  Exception{
        CSVReader reader = new CSVReader();
        List<Ausgaben> ausgaben= reader.readAusgaben("ausgaben.csv");
        assertThat(ausgaben).hasSize(6);
    }
}
