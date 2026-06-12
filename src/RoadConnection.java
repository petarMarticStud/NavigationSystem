/**
 * Repräsentiert eine gewichtete Edge
 * (Straßenverbindung) zwischen zwei Städten.
 */

public class RoadConnection {


    private City destinationCity;

    private int distanceInKilometers;


    /**
     * Konstruktor
     *
     * @param destinationCity Zielstadt
     * @param distanceInKilometers Entfernung in km
     */
    public RoadConnection(City destinationCity, int distanceInKilometers) {
        this.destinationCity = destinationCity;
        this.distanceInKilometers = distanceInKilometers;
    }

    /**
     * Gibt die Zielstadt zurück.
     *
     * @return Zielstadt
     */
    public City getDestinationCity() {
        return destinationCity;
    }

    /**
     * Gibt die Entfernung zurück.
     *
     * @return Entfernung in km
     */
    public int getDistanceInKilometers() {
        return distanceInKilometers;
    }
}
