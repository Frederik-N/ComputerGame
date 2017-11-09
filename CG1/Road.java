
public class Road implements Comparable<Road> {
    private City from;
    private City to;
    private int length;
    public Road(City from, City to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    /**
     * @return where the road starts
     */
    public City getFrom() {
        return from;
    }

    /**
     * @return where the road ends
     */
    public City getTo() {
        return to;
    }

    /**
     * @return the length of the road
     */
    public int getLength() {
        return length;
    }

    public int compareTo(Road r) {
        if(this.from.equals(r.from)) {
            return this.to.compareTo(r.to);
        }
        return this.from.compareTo(r.from);
    }
}
