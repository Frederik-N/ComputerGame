
public class Position {
    private City from;
    private City to;
    private int distance;
    private int total;

    public Position(City from, City to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.total = distance;
    }

    /**
     * The city that is currently "from"
     * @return the city you move from
     */
    public City getFrom() {
        return from;
    }

    /**
     * The city that is currently "to"
     * @return the city you're moving to
     */
    public City getTo() {
        return to;
    }

    /**
     * @return the distance left to the "to" city
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @return the total distance between the cities
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
