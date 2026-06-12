import java.util.*;

/**
 * Gewichteter Graph (Weighted Graph)
 * zur Speicherung des Straßennetzes.
 *
 * Vertices = Städte
 * Edges = Straßenverbindungen
 */


public class RoadNetworkGraph {

    /**
     * Adjazenzliste des Graphen.
     *
     * Jeder Stadt werden ihre
     * Straßenverbindungen zugeordnet.
     */
    private Map<City, List<RoadConnection>> roadNetwork;

    /**
     * Initialisiert einen leeren Graphen.
     */
    public RoadNetworkGraph() {
        roadNetwork = new HashMap<>();
    }

    /**
     * Fügt eine Stadt hinzu.
     *
     * @param city Stadt
     */
    public void addCity(City city) {

        // Verhindert das Überschreiben bestehender Verbindungen,
        // falls die Stadt bereits existiert.
        roadNetwork.putIfAbsent(city, new ArrayList<>());
    }

    /**
     * Fügt eine Straße zwischen
     * zwei Städten hinzu.
     *
     * @param startCity Startstadt
     * @param destinationCity Zielstadt
     * @param distanceInKilometers Entfernung
     */
    public void addRoad(City startCity, City destinationCity, int distanceInKilometers) {
        addCity(startCity);
        addCity(destinationCity);

        // Da es sich um einen ungerichteten Graphen handelt,
        // müssen beide Richtungen hinzugefügt werden.

        // Verbindung von Start- zu Zielstadt hinzufügen
        roadNetwork.get(startCity)
                .add(new RoadConnection(destinationCity,distanceInKilometers));

        // Rückverbindung hinzufügen
        roadNetwork.get(destinationCity)
                .add(new RoadConnection(startCity, distanceInKilometers));
    }

    /**
     * Gibt die Adjazenzliste des Graphen zurück.
     *
     * @return Straßennetz
     */
    public Map<City, List<RoadConnection>> getRoadNetwork() {
        return roadNetwork;
    }

    /**
     * Gibt alle Städte aus.
     */
    public void showAllCities() {

        System.out.println("\nVerfügbare Städte:");

        for (City city : roadNetwork.keySet()) {
            System.out.println("- " + city);
        }
    }




    /**
     * Sucht eine Stadt anhand ihres Namens.
     *
     * @param cityName Stadtname
     * @return Gefundene Stadt oder null
     */
    public City findCityByName(
            String cityName) {

        // Durchsucht die Schlüssel der Adjazenzliste
        // nach einer Stadt mit passendem Namen.
        for (City city : roadNetwork.keySet()) {

            // Vergleicht die Namen der Städte (Ignoriert Groß-/Kleinschreibung).
            if (city.getCityName()
                    .equalsIgnoreCase(cityName)) {

                return city;
            }
        }

        // Wenn keine Stadt gefunden wird, wird null zurückgegeben.
        return null;
    }


    /**
    // ############# ALGORITHMEN ################
     */



    /**
     * Traversiert den Graphen mittels
     * Depth First Search (DFS).
     *
     * @param startCity Startvertex
     */
    public void showReachableCitiesDFS(City startCity) {
        // Set zur Verfolgung besuchter Städte, um Endlosschleifen zu vermeiden.
        Set<City> visitedCities = new HashSet<>();

        // Startet die rekursive DFS Traversierung.
        dfsRecursive(startCity, visitedCities);
    }

    /**
     * Rekursive DFS Traversierung.
     *
     * @param currentCity Aktueller Vertex
     * @param visitedCities Bereits besuchte Vertices
     */
    private void dfsRecursive(City currentCity, Set<City> visitedCities) {
        // Wenn die aktuelle Stadt bereits besucht wurde, wird die Rekursion gestoppt.
        if (visitedCities.contains(currentCity)) {
            return;
        }

        // Markiert die aktuelle Stadt als besucht.
        visitedCities.add(currentCity);
        System.out.println(currentCity);


        // Holt die Straßenverbindungen der aktuellen Stadt.
        List<RoadConnection> roads =
                roadNetwork.get(currentCity);

        // Rekursive DFS Traversierung für alle benachbarten Städte.
        for (RoadConnection road : roads) {

            // Holt die benachbarte Stadt am Ende der Straße.
            City neighboringCity = road.getDestinationCity();

            // Rekursive DFS Traversierung für die benachbarte Stadt.
            dfsRecursive(
                    neighboringCity,
                    visitedCities
            );
        }
    }




    /**
     * Traversiert den Graphen mittels
     * Breadth First Search (BFS).
     *
     * @param startCity Startvertex
     * @param destinationCity Zielvertex
     */
    public void findRouteWithFewestStopsBFS(City startCity, City destinationCity) {

        // Queue für die BFS Traversierung,
        // um die Städte in der Reihenfolge ihres Besuchs zu speichern.
        Queue<City> citiesToVisit =
                new LinkedList<>();

        // Set zur Verfolgung besuchter Städte, um Endlosschleifen zu vermeiden.
        Set<City> visitedCities =
                new HashSet<>();

        citiesToVisit.add(startCity);

        // Markiert die Startstadt als besucht, um zu verhindern, dass sie erneut in die Queue aufgenommen wird.
        visitedCities.add(startCity);

        // BFS Traversierung, bis die Queue leer ist.
        while (!citiesToVisit.isEmpty()) {

            // Holt die nächste Stadt aus der Queue.
            City currentCity = citiesToVisit.poll();

            // Überprüft, ob die aktuelle Stadt das Ziel ist.
            if (currentCity.equals(destinationCity)) {
                System.out.println("\nZiel erreicht: " + currentCity);
                return;
            }

            // Holt die Straßenverbindungen der aktuellen Stadt.
            List<RoadConnection> roads = roadNetwork.get(currentCity);

            // Fügt alle benachbarten Städte,
            // die noch nicht besucht wurden, zur Queue hinzu.
            for (RoadConnection road : roads) {

                // Holt die benachbarte Stadt am Ende der Straße.
                City neighboringCity = road.getDestinationCity();
                System.out.println("Besuche: " + currentCity);


                // Wenn die benachbarte Stadt noch nicht besucht wurde,
                // wird sie zur Queue hinzugefügt und als besucht markiert.
                if (!visitedCities.contains(neighboringCity)) {

                    visitedCities.add(
                            neighboringCity
                    );

                    // Fügt die benachbarte Stadt zur Queue hinzu, damit sie in zukünftigen Iterationen besucht wird.
                    citiesToVisit.add(
                            neighboringCity
                    );
                }
            }
        }
    }




    /**
     * Berechnet die kürzeste Distanz
     * zwischen zwei Städten.
     *
     * @param startCity Startvertex
     * @param destinationCity Zielvertex
     */
    public void findShortestRouteDijkstra(City startCity, City destinationCity) {

        // Map zur Speicherung der kürzesten bekannten Distanz
        // von der Startstadt zu jeder anderen Stadt.
        Map<City, Integer> shortestDistances = new HashMap<>();

        // Initialisiert alle Distanzen mit unendlich (Integer.MAX_VALUE),
        for (City city : roadNetwork.keySet()) {
            shortestDistances.put(city, Integer.MAX_VALUE);
        }

        // Die Distanz zur Startstadt ist 0, da wir uns bereits dort befinden.
        shortestDistances.put(startCity, 0);

        // PriorityQueue zur Auswahl der Stadt mit der aktuell kürzesten bekannten Distanz.
        PriorityQueue<City> citiesToVisit =  new PriorityQueue<>
                (Comparator.comparingInt(shortestDistances::get));

        // Fügt die Startstadt zur PriorityQueue hinzu,
        // damit sie als erstes besucht wird.
        citiesToVisit.add(startCity);


        while (!citiesToVisit.isEmpty()) {
            // Holt die Stadt mit der aktuell kürzesten bekannten Distanz
            // aus der PriorityQueue.
            City currentCity = citiesToVisit.poll();
            System.out.println("\nAktuelle Stadt: " + currentCity);

            //Strassenverbindungen der aktuellen Stadt abrufen
            List<RoadConnection> roads = roadNetwork.get(currentCity);

            // Für jede Straßenverbindung der aktuellen Stadt
            // wird die potenzielle neue Distanz zur benachbarten Stadt berechnet.
            for (RoadConnection road : roads) {

                // Holt die benachbarte Stadt am Ende der Straße.
                City neighboringCity = road.getDestinationCity();

                // Holt die aktuell bekannte kürzeste Distanz zur aktuellen Stadt.
                int currentDistance = shortestDistances.get(currentCity);
                // Holt die Entfernung der Straße zur benachbarten Stadt.
                int roadDistance = road.getDistanceInKilometers();

                // Berechnet die potenzielle neue Distanz zur benachbarten Stadt über die aktuelle Stadt.
                int newDistance = currentDistance + roadDistance;

                // Wenn die potenzielle neue Distanz kürzer ist, wird sie priorisiert.
                if (newDistance < shortestDistances.get(neighboringCity))
                {
                    // Aktualisiert die kürzeste bekannte Distanz zur benachbarten Stadt.
                    shortestDistances.put(neighboringCity, newDistance);
                    citiesToVisit.add(neighboringCity);

                    System.out.println(
                            "Neue Distanz zu " + neighboringCity + " gefunden: "
                                    + newDistance + " km");
                }
            }

            System.out.println("Kürzeste Distanz von " + startCity + " nach " + destinationCity + ": "
                            + shortestDistances.get(destinationCity) + " km");
        }
    }
}
