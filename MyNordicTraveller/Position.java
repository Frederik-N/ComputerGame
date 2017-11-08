
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

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }

    public int getTotal() {
        return total;
    }

    public boolean hasArrived() {
        if(distance==0) {
            return true;
        }
        return false;
    }

    public boolean move() {
        if(distance>0) {
            distance--;
            return true;
        }
        return false;
    }

    public void turnAround() {
        distance = Math.abs(distance-total);
        City nTo = to;
        to = from;
        from = nTo;
    }
}
