
public class City implements Comparable<City> {
    private String name;
    private int value;
    private int initialValue;

    public City(String name, int value) {
        this.name = name;
        this.value = value;
        initialValue = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void changeValue(int amount) {
        value += amount;
    }

    public void reset() {
        value = initialValue;
    }

    public int compareTo(City c) {
        return this.name.compareTo(c.name);
    }
}
