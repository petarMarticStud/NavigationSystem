/**
 * Repräsentiert einen Vertex (Stadt)
 * im Straßennetz.
 */
public class City {

    private String cityName;

    /**
     * Erstellt eine neue Stadt.
     *
     * @param cityName Name der Stadt
     */
    public City(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Gibt den Namen der Stadt zurück.
     *
     * @return Stadtname
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Wird für die Ausgabe verwendet.
     */
    @Override
    public String toString() {
        return cityName;
    }

    /**
     * Zwei Städte sind gleich, wenn ihr Name gleich ist (Ignoriert Groß-/Kleinschreibung).
     *
     * WARUM NOTWENDIG FÜR GRAPH-ALGORITHMEN:
     * Standardmäßig vergleicht Java nur die Speicheradresse. Wenn BFS/DFS/Dijkstra ein neues
     * City-Objekt mit demselben Namen erzeugen, muss Java erkennen, dass es sich um
     * denselben Knoten (Vertex) im Graphen handelt. Ohne diese Methode drohen
     * Endlosschleifen, da bereits besuchte Städte nicht wiedererkannt werden.
     */

    @Override
    public boolean equals(Object obj) {
        // Schneller Check: Wenn beide Referenzen gleich sind, sind die Städte gleich.
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof City)) {
            return false;
        }

        City other = (City) obj;

        // Vergleicht die Stadtnamen, ignoriert dabei Groß-/Kleinschreibung.
        return cityName.equalsIgnoreCase(
                other.cityName
        );
    }

    /**
     * Generiert einen konsistenten Hashcode basierend auf dem kleingeschriebenen Stadtnamen.
     *
     * WARUM NOTWENDIG FÜR MAPS UND SETS:
     * Datenstrukturen wie HashMap (für Distanzen bei Dijkstra) und HashSet (für das visited-Set
     * bei BFS/DFS) nutzen den Hashcode, um Objekte blitzschnell in "Postfächern" abzuspeichern.
     * Da equals() den Namen ignoriert, MUSS auch hashCode() für "Berlin" und "berlin"
     * exakt dieselbe Zahl liefern. Andernfalls findet die Map den Knoten nicht wieder.
     */

    @Override
    public int hashCode() {
        return cityName.toLowerCase().hashCode();
    }
}
