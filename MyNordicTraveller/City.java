
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

    /**
     * @return value for this city
     */
    public int getValue() {
        return value;
    }

    /**
     * @return name for this city
     */
    public String getName() {
        return name;
    }

    /**
     * @return country for this city
     */
    public Country getCountry() {
        return country;
    }

    /**
     *  Adds a specific amount to value
     * @param amount the amount to add to value
     */
    public void changeValue(int amount) {
        value += amount;
    }

    /**
     * Sets value to its initial value
     */
    public void reset() {
        value = initialValue;
    }

    public int compareTo(City c) {
        return this.name.compareTo(c.name);
    }

    /**
     * Rolls a random number(bonus) and subtracts it from value.
     * @return bonus that were substracted from value
     */
    public int arrive() {
        int bonus = country.bonus(value);
        value -= bonus;
        return bonus;
    }


}
