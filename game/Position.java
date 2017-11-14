/**
 * Position describes where the player is currently at, and
 * can be used to see possible destinations, moving and turning around
 * @author Jonas Madsen || Frederik Nielsen
 * @version 14.11.2017
 */
public class Position {
    /** Cities that the player is between */
    private City from;
    private City to;
    /** Distance left to "to" city */
    private int distance;
    /** Distance between "to" and "from" city */
    private int total;

    /**
     * Creates a new Position object
     * @param from the city you're moving from
     * @param to the city you're moving to
     * @param distance distance from you to the "to" city
     */
    public Position(City from, City to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.total = distance;
    }

    /**
     * Returns the city that is currently "from"
     * @return the city you move from
     */
    public City getFrom() {
        return from;
    }

    /**
     * Returns the city that is currently "to"
     * @return the city you're moving to
     */
    public City getTo() {
        return to;
    }

    /**
     * Returns the distance left to the "to" city
     * @return distance to "to" city
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Returns the total distance between the cities
     * @return distance from "to" to "from" city
     */
    public int getTotal() {
        return total;
    }

    /**
     * Checks if the player has arrived at the city.
     * @return true if at the city, false if not
     */
    public boolean hasArrived() {
        return (distance==0);
    }

    /**
     * Moves 1 closer to the "to" city.
     * @return true if distance above 0, false if not
     */
    public boolean move() {
        if(distance>0) {
            distance--;
            return true;
        }
        return false;
    }

    /**
     * Turns the player around, switching the "to" city and the "from" city
     */
    public void turnAround() {
        distance = Math.abs(distance-total);
        City nTo = to;
        to = from;
        from = nTo;
    }
}
