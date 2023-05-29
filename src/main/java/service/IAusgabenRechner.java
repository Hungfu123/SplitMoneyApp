package service;

import model.Ausgaben;

import java.util.List;
import java.util.Map;

public interface IAusgabenRechner {
        Double getGesamtsumme(List<Ausgaben> ausgaben);
        int getAnzahlPersonen(List<Ausgaben> ausgaben);
        double rechneAverageDebtPerPerson(List<Ausgaben> ausgaben);
        Map<String, Map<String, Double>> erstelleSchuldenMap(List<Ausgaben> ausgaben);

}
