import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Stellt die Benutzeroberfläche des
 * Navigationssystems bereit.
 */
public class NavigationSystem {

    private final RoadNetworkGraph roadNetworkGraph;
    private final Scanner scanner;

    /**
     * Initialisiert das Navigationssystem.
     */

    /**
     * Erstellt ein Beispiel-Straßennetz.
     */
    private void initializeSampleRoadNetwork() {

        City vienna = new City("Wien");
        City linz = new City("Linz");
        City graz = new City("Graz");
        City salzburg = new City("Salzburg");
        City innsbruck = new City("Innsbruck");
        City klagenfurt = new City("Klagenfurt");

        roadNetworkGraph.addCity(vienna);
        roadNetworkGraph.addCity(linz);
        roadNetworkGraph.addCity(graz);
        roadNetworkGraph.addCity(salzburg);
        roadNetworkGraph.addCity(innsbruck);
        roadNetworkGraph.addCity(klagenfurt);

        roadNetworkGraph.addRoad(vienna, linz, 180);
        roadNetworkGraph.addRoad(vienna, graz, 200);
        roadNetworkGraph.addRoad(linz, salzburg, 130);
        roadNetworkGraph.addRoad(salzburg, innsbruck, 190);
        roadNetworkGraph.addRoad(graz, klagenfurt, 140);
        roadNetworkGraph.addRoad(graz, salzburg, 280);
    }

    public NavigationSystem() {

        roadNetworkGraph = new RoadNetworkGraph();
        scanner = new Scanner(System.in);

        initializeSampleRoadNetwork();
    }





    /**
     * Startet das Hauptmenü.
     */
    public void start()
    {boolean running = true;

        while (running)
        {

            showMenu();

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    addCity();
                    break;

                case 2:
                    addRoad();
                    break;

                case 3:
                    roadNetworkGraph.showAllCities();
                    break;

                case 4:
                    displayRoadNetwork();
                    break;

                case 5:
                    startDFS();
                    break;

                case 6:
                    startBFS();
                    break;

                case 7:
                    startDijkstra();
                    break;

                case 8:
                    clearScreen();
                    break;

                case 0:
                    running = false;
                    System.out.println(
                            "\nProgramm beendet."
                    );
                    break;

                default:
                    System.out.println(
                            "\nUngültige Eingabe."
                    );
            }
        }
    }

    private void clearScreen() {
        // ANSI Escape Code zum Löschen des Bildschirms
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    /**
     * Zeigt das Hauptmenü.
     */
    private void showMenu() {

        System.out.println();
        System.out.println(
                "===== SMART NAVIGATION SYSTEM ====="
        );
        System.out.println(
                "1. Stadt hinzufügen"
        );
        System.out.println(
                "2. Straße hinzufügen"
        );
        System.out.println(
                "3. Alle Städte anzeigen"
        );
        System.out.println(
                "4. Straßennetz anzeigen"
        );
        System.out.println(
                "5. DFS starten"
        );
        System.out.println(
                "6. BFS starten"
        );
        System.out.println(
                "7. Dijkstra starten"
        );
        System.out.println(
                "8. Bildschirm löschen"
        );

        System.out.println(
                "0. Beenden"
        );

        System.out.print(
                "\nAuswahl: "
        );
    }

    /**
     * Fügt eine Stadt hinzu.
     */
    private void addCity() {

        System.out.print(
                "\nName der Stadt: "
        );

        String cityName =
                scanner.nextLine();

        City city =
                new City(cityName);

        roadNetworkGraph.addCity(city);

        System.out.println(
                "\nStadt hinzugefügt."
        );
    }

    /**
     * Fügt eine Straße hinzu.
     */
    private void addRoad() {

        System.out.print("\nStartstadt: ");
        String startCityName = scanner.nextLine();

        System.out.print("Zielstadt: ");
        String destinationCityName = scanner.nextLine();

        System.out.print("Entfernung (km): ");
        int distance = Integer.parseInt(scanner.nextLine());

        City startCity = roadNetworkGraph.findCityByName(startCityName);
        City destinationCity = roadNetworkGraph.findCityByName(destinationCityName);

        if (startCity == null || destinationCity == null) {
            System.out.println("\nEine oder beide Städte existieren nicht.");
            return;
        }

        roadNetworkGraph.addRoad(startCity, destinationCity, distance);

        System.out.println("\nStraße hinzugefügt.");
    }

    /**
     * Visualisiert das gesamte Straßennetz.
     */
    private void displayRoadNetwork() {

        System.out.println("\n===== STRASSENNETZ =====");
        Map<City, List<RoadConnection>> roadNetwork = roadNetworkGraph.getRoadNetwork();

        for (City city : roadNetwork.keySet()) {
            System.out.println("\n" + city);

            List<RoadConnection> roads = roadNetwork.get(city);

            if (roads.isEmpty()) {
                System.out.println("Keine Verbindungen");
                continue;
            }

            for (RoadConnection road : roads) {

                System.out.println(
                        "   └─ "
                                + road.getDestinationCity()
                                + " ("
                                + road.getDistanceInKilometers()
                                + " km)"
                );
            }
        }
    }

    /**
     * Startet DFS.
     */
    private void startDFS() {

        System.out.print("\nStartstadt:");

        String cityName = scanner.nextLine();
        City startCity = roadNetworkGraph.findCityByName(cityName);

        if (startCity == null) {
            System.out.println("\nStadt nicht gefunden.");
            return;
        }
        roadNetworkGraph.showReachableCitiesDFS(startCity);
    }

    /**
     * Startet BFS.
     */
    private void startBFS() {

        System.out.print("\nStartstadt: ");
        String startCityName = scanner.nextLine();

        System.out.print("Zielstadt: ");
        String destinationCityName = scanner.nextLine();

        City startCity = roadNetworkGraph.findCityByName(startCityName);
        City destinationCity = roadNetworkGraph.findCityByName(destinationCityName);

        if (startCity == null || destinationCity == null) {
            System.out.println("\nStadt nicht gefunden.");
            return;
        }

        System.out.println("Suche Route von " + startCity + " nach " + destinationCity);
        roadNetworkGraph.findRouteWithFewestStopsBFS(startCity,destinationCity);
    }

    /**
     * Startet Dijkstra.
     */
    private void startDijkstra() {

        System.out.print("\nStartstadt: ");
        String startCityName = scanner.nextLine();

        System.out.print("Zielstadt: ");
        String destinationCityName = scanner.nextLine();

        City startCity = roadNetworkGraph.findCityByName(startCityName);
        City destinationCity = roadNetworkGraph.findCityByName(destinationCityName);


        if (startCity == null || destinationCity == null) {
            System.out.println("\nStadt nicht gefunden.");
            return;
        }

        roadNetworkGraph.findShortestRouteDijkstra(startCity, destinationCity);
    }
}
