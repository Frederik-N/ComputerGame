/**
 * A city is placed within a country and has a specific value, that reduces as the players arrive
 * @author Jonas Madsen || Frederik Nielsen
 * @version 18.11.2017
 */
public class City implements Comparable<City> {
    /** Name of this city */
    private String name;
    /** Values for this city */
    private int value;
    private int initialValue;
    /** Country the city is placed within */
    private Country country;

    /**
     * Creates a new City object
     * @param name Name of this city
     * @param value Value of this city
     * @param country Which country the city is placed within
     */
    public City(String name, int value, Country country) {
        this.name = name;
        this.value = value;
        initialValue = value;
        this.country = country;
    }

    /**
     * Returns the value for this
     * @return value of city
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the name for this city
     * @return name of city
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the country for this city
     * @return country of this city
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

    /**
     * Compares this city with specified city for ordering
     * @param c city to be compared
     * @return negative, 0 or positive as this city is less than, equal to, or greater than the specified city
     */
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
