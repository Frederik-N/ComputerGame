
public class City implements Comparable<City> {
    private String name;
    private int value;
    private int initialValue;
    private Country country;

    public City(String name, int value, Country country) {
        this.name = name;
        this.value = value;
        initialValue = value;
        this.country = country;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
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

    public int arrive() {
        int bonus = country.bonus(value);
        value -= bonus;
        return bonus;
    }


}
