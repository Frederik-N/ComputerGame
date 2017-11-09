
public class Road implements Comparable<Road> {
    private City from;
    private City to;
    private int length;
    public Road(City from, City to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

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
