/**
 * Represents a road between two cities.
 * A one-directional road is made between two cities and has a specific length.
 * Therefore a road from city A to city B is not the same as from city B to city A.
 *
 * @author Frederik Nielsen || Jonas Madsen
 * @version 18.11.2017
 */
public class Road implements Comparable<Road> {
    /** References to cities that this road connects. */
    private City from;
    private City to;

    /** Length of this road. */
    private int length;

    /**
     * creates a new Road object.
     * @param from   the city the road starts in.
     * @param to     the city the road ends in.
     * @param length the length of the road.
     */
    public Road(City from, City to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    /**
     * Where the road is starts/is from
     * @return where the road starts
     */
    public City getFrom() {
        return from;
    }

    /**
     * Where the road is going to/ends
     * @return where the road ends
     */
    public City getTo() {
        return to;
    }

    /**
     * Returns the length of the road
     * @return length of road.
     */
    public int getLength() {
        return length;
    }

    /**
     * Compares this road with the specified road for ordering.
     * @param r Road that is being compared.
     * @return a negative integer, zero, or a positive integer as this road is less than, equal to, or greater than the specified road.
     */
    public int compareTo(Road r) {
        if(this.from.equals(r.from)) {
            return this.to.compareTo(r.to);
        }
        return this.from.compareTo(r.from);
    }
}
