package splitter;

import java.util.*;
import java.util.stream.Collectors;

public class AusgabenRechner {
    private final List<Ausgaben> ausgaben;
    public AusgabenRechner(List<Ausgaben> ausgaben){
        this.ausgaben = ausgaben;
    }

    public Map<String,Double> summiereEinzelneAusgaben() {
        //Gruppieren der Ausgaben nach Namen
        return ausgaben.stream()
                .collect(Collectors.groupingBy(
                                Ausgaben::getName,
                                Collectors.summingDouble(Ausgaben::getAusgabe)));
    }
    public Double getGesamtsumme() {
        return ausgaben.stream()
                .mapToDouble(Ausgaben::getAusgabe)
                .sum();
    }

    public int getAnzahlPersonen() {
        Map <String,Double> einzelneAusgaben = summiereEinzelneAusgaben();
        return einzelneAusgaben.keySet().size();
    }

    // rechnet die durchschnittlichen Schulden aus die jede Person zahlen muss.
    public double rechneAverageDebtPerPerson(){
        return getGesamtsumme()/getAnzahlPersonen();
    }

    //1. Fall ausgabe < Spass pro Person: Person hat Schulden X
    //2. Fall ausgabe >= Spass pro Person: Person muss keine Schulden bezahlen X

    // Sortiert einzelne Schulden und gibt sie als sortierte LinkedHashMap zurück
    private Map<String, Double> sortiereEinzelneSchulden() {
        Double averageDebtPerPerson = rechneAverageDebtPerPerson();
        Map<String,Double> deptMap = summiereEinzelneAusgaben();
        return deptMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> averageDebtPerPerson -e.getValue(),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

public List<String> erstelleSchuldenListe() {

    Map<String, Double> debtMap = sortiereEinzelneSchulden();
    List<Map.Entry<String, Double>> sortedDebts = new ArrayList<>(debtMap.entrySet());
    List<String> schuldenListe = new ArrayList<>();

    int negativerIndex = 0;
    int positiverIndex = sortedDebts.size() - 1;

    begleicheSchulden(debtMap, sortedDebts, schuldenListe, negativerIndex, positiverIndex);
    return schuldenListe;
}
    //Person mit dem höchsten niedrigen Wert wird von der anderen Person mit dem höchsten Schuldwert bezahlt,
    // rechnet die einzelnen Schulden pro Person. Wenn die Zahl < 0 ist, dann hat die Person keine Schulden
    private void begleicheSchulden(Map<String, Double> debtMap, List<Map.Entry<String, Double>> sortedDebts, List<String> schuldenListe, int negativerIndex, int positiverIndex) {
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
            addSchuldenInfoInListe(schuldenListe, negativerSchuldner, positiverSchuldner, minBeitrag);
            if(andererSchuldBeitrag == 0.0) {
                positiverIndex--;
            }
            if(schuldenBeitrag == 0.0) {
                negativerIndex++;
            }
        }
    }

    private static void addSchuldenInfoInListe(List<String> schuldenListe, Map.Entry<String, Double> negativerSchuldner, Map.Entry<String, Double> positiverSchuldner, double minBeitrag) {
        String schuldenInfo = positiverSchuldner.getKey() + " schuldet " + negativerSchuldner.getKey() + " "+ minBeitrag + " Euro";
        schuldenListe.add(schuldenInfo);
    }

    private boolean hatAusstehendeSchulden(Map<String, Double> debtMap) {
        return debtMap.values().stream()
                .anyMatch(betrag -> betrag != 0.0);
    }

}
