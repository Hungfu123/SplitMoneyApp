# SplitMoneyApp

Die SplitMoneyApp ist eine kleine Anwendung ohne Benutzeroberfläche (UI), die entwickelt wurde, um mit Streams und Datenstrukturen zu arbeiten. Sie bietet eine Lösung für das Aufteilen von Ausgaben, insbesondere in Situationen, in denen mehrere Personen unterschiedliche Beträge bezahlt haben.

## Funktionsweise

- **CSV-Dateieingabe:**
    - Die Anwendung erwartet eine Eingabe in Form einer CSV-Datei, in der festgehalten wird, wer welche Ausgaben getätigt hat. Dies ist besonders nützlich für Gruppenaktivitäten wie Städtereisen, bei denen verschiedene Personen unterschiedliche Kosten übernehmen.

- **Berechnung der Ausgaben:**
    - Falls mehrere Einträge denselben Namen haben, wird davon ausgegangen, dass es sich um dieselbe Person handelt. Die App aggregiert die Ausgaben für jede Person und berechnet am Ende einen gleichmäßigen Ausgleich, sodass nur eine Person am Ende Geld zurückgeben muss.

## Technologiestack

- **Backend:**
    - Java 17
    - Gradle

- **Datenverarbeitung:**
    - Verwendung von Streams und Datenstrukturen für die Verarbeitung von Ausgabeninformationen

- **Dateiformat:**
    - CSV (Comma-Separated Values) für die Eingabe von Ausgabeninformationen

## Nutzung

Um die Anwendung zu verwenden, führe die folgenden Schritte aus:

1. Klone das Repository:
   ```bash
   git clone https://github.com/DeinBenutzername/SplitMoneyApp.git

2. Passe die CSV-Datei mit den Ausgaben an deine Bedürfnisse an in "ausgaben.csv".

3. Führe die Anwendung aus:

    ```bash
    ./gradlew run
   
Folge den Anweisungen der Anwendung, um den gleichmäßigen Ausgleich zu berechnen.

Beispiel
Hier ist ein einfaches Beispiel für das CSV-Dateiformat:

ausgaben.csv
```bash
Person1, 30.00
Person2, 20.00
Person1, 15.00
Person3, 25.00