package service;

import model.Ausgaben;

import java.util.*;
import java.util.stream.Collectors;

public class AusgabenRechner implements IAusgabenRechner {

    private Map<String,Double> summiereEinzelneAusgaben(List<Ausgaben> ausgaben) {
        //Gruppieren der Ausgaben nach Namen
        return ausgaben.stream()
                .collect(Collectors.groupingBy(
                                Ausgaben::getNAME,
                                Collectors.summingDouble(Ausgaben::getAUSGABE)));
    }
    public Double getGesamtsumme(List<Ausgaben> ausgaben) {
        return ausgaben.stream()
                .mapToDouble(Ausgaben::getAUSGABE)
                .sum();
    }

    public int getAnzahlPersonen(List<Ausgaben> ausgaben) {
        Map <String,Double> einzelneAusgaben = summiereEinzelneAusgaben(ausgaben);
        return einzelneAusgaben.size();
    }

    // rechnet die durchschnittlichen Schulden aus die jede Person zahlen muss.
    public double rechneAverageDebtPerPerson(List<Ausgaben> ausgaben){
        return getGesamtsumme(ausgaben)/getAnzahlPersonen(ausgaben);
    }

    //1. Fall ausgabe < Spass pro Person: Person hat Schulden X
    //2. Fall ausgabe >= Spass pro Person: Person muss keine Schulden bezahlen X

    // Sortiert einzelne Schulden und gibt sie als sortierte LinkedHashMap zurück
    private Map<String, Double> sortiereEinzelneSchulden(List<Ausgaben> ausgaben) {
        Double averageDebtPerPerson = rechneAverageDebtPerPerson(ausgaben);
        Map<String,Double> deptMap = summiereEinzelneAusgaben(ausgaben);
        return deptMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> averageDebtPerPerson -e.getValue(),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

public Map<String, Map<String, Double>> erstelleSchuldenMap(List<Ausgaben> ausgaben) {
    Map<String, Double> debtMap = sortiereEinzelneSchulden(ausgaben);
    Map<String, Map<String, Double>> schuldenMap = new LinkedHashMap<>();

    begleicheSchulden(debtMap, schuldenMap);
    return schuldenMap;
}
    //Person mit dem höchsten niedrigen Wert wird von der anderen Person mit dem höchsten Schuldwert bezahlt,
    // rechnet die einzelnen Schulden pro Person. Wenn die Zahl < 0 ist, dann hat die Person keine Schulden
    private void begleicheSchulden(Map<String, Double> debtMap, Map<String, Map<String, Double>> schuldenMap) {
        List<Map.Entry<String, Double>> sortedDebts = new ArrayList<>(debtMap.entrySet());
        int negativerIndex = 0;
        int positiverIndex = sortedDebts.size() - 1;

        while (hatAusstehendeSchulden(debtMap)) {
            Map.Entry<String, Double> negativerSchuldner = sortedDebts.get(negativerIndex);
            Map.Entry<String, Double> positiverSchuldner = sortedDebts.get(positiverIndex);

            double negativerBetrag = negativerSchuldner.getValue();
            double positiverBetrag = positiverSchuldner.getValue();

            if (negativerBetrag < 0) {
                negativerBetrag = -negativerBetrag;
            }

            double minBeitrag = Math.min(negativerBetrag, positiverBetrag);

            double schuldenBeitrag = negativerBetrag - minBeitrag;
            double andererSchuldBeitrag = positiverBetrag - minBeitrag;

            debtMap.put(negativerSchuldner.getKey(), schuldenBeitrag);
            debtMap.put(positiverSchuldner.getKey(), andererSchuldBeitrag);

            addSchuldenInfoInMap(schuldenMap, negativerSchuldner, positiverSchuldner, minBeitrag);

            // Wenn bei einer Map die Schulden beglichen sind, dann nehmen wir das nächste Entry
            if (andererSchuldBeitrag == 0.0) {
                positiverIndex--;
            }
            if (schuldenBeitrag == 0.0) {
                negativerIndex++;
            }
        }
    }


    private static void addSchuldenInfoInMap(Map<String, Map<String, Double>> schuldenMap, Map.Entry<String, Double> negativerSchuldner, Map.Entry<String, Double> positiverSchuldner, double minBeitrag) {
        //String schuldenInfo = positiverSchuldner.getKey() + " schuldet " + negativerSchuldner.getKey() + " "+ minBeitrag + " Euro";
        String negativerSchuldnerName = negativerSchuldner.getKey();
        String positiverSchuldnerName = positiverSchuldner.getKey();
        double aktuellerSchuldenBetrag = minBeitrag;

        if (schuldenMap.containsKey(negativerSchuldnerName)) {
            Map<String, Double> schuldenSubMap = schuldenMap.get(negativerSchuldnerName);
            schuldenSubMap.put(positiverSchuldnerName, aktuellerSchuldenBetrag);
        } else {
            Map<String, Double> schuldenSubMap = new LinkedHashMap<>();
            schuldenSubMap.put(positiverSchuldnerName, aktuellerSchuldenBetrag);
            schuldenMap.put(negativerSchuldnerName, schuldenSubMap);
        }
    }

    private boolean hatAusstehendeSchulden(Map<String, Double> debtMap) {
        return debtMap.values().stream()
                .anyMatch(betrag -> betrag != 0.0);
    }

}

