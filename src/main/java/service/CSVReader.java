package service;

import model.Ausgaben;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader implements ICSVReader{
    private final String filepath;
    public  CSVReader(String filepath){

        this.filepath = filepath;
    }
// Liest die Zeilen der CSV und wandelt jede Zeile in eine Ausgabeninstanz um
// und wandelt dies in eine Liste
    public List<Ausgaben> readAusgaben() throws IOException {
        Stream<String> zeilen = Files.lines(Paths.get(filepath));
        return zeilen
                .map(this::parseAusgabenVonCSV)
                .filter(ausgabe -> ausgabe !=null)
                .collect(Collectors.toList());
    }

    public Map<String, Double> readAusgaben3() throws IOException {
        Stream<String> zeilen = Files.lines(Paths.get(filepath));
        return zeilen
                .map(this::parseAusgabenVonCSV)
                .filter(ausgabe -> ausgabe != null)
                .collect(Collectors.toMap(
                        Ausgaben::getNAME,
                        Ausgaben::getAUSGABE
                ));
    }
    //parst die Zeilen der CSV in die Ausgaben Instanz
    private Ausgaben parseAusgabenVonCSV(String line) {
        String[] parts = line.split(",");
        if(parts.length == 2) {
            String name = parts[0].trim();
            double beitrag = Double.parseDouble(parts[1].trim());
            return new Ausgaben(name,beitrag);
        }
        return null;
    }



}
