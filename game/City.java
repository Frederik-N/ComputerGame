/**
 * A city is placed within a country and has a specific value, that reduces as the players arrive
 * @author Jonas Madsen || Frederik Nielsen
 * @version 26.11.2017
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
     * Rolls a random number(bonus) and subtracts it from value if bonus > 0.
     * @return bonus
     */
    public int arrive() {
        int bonus = country.bonus(value);
        if(bonus>0) {
            value -= bonus;
            return bonus;
        }
        return bonus;
    }

    /**
     * Makes a player roll a random number(bonus) and subtracts it from value if bonus > 0.
     * @param p A player.
     * @return arrive() which returns bonus.
     */
    public int arrive (Player p) {
        return arrive();
    }

    /**
     *  Indicates whether an other city is equal to this one.
     * @param o The object that is being compared.
     * @return True if this object is the same as the other, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if(this == o) { return true;}
        if (o == null) { return false;}
        if(getClass() != (o.getClass())) {return false;}
        City city = (City) o;
        return name.equals(city.getName()) && country.equals(city.getCountry());
    }

    /**
     * Returns a hashcode value for the object.
     * @return Hashcode value
     */
    public int hashCode() {
        return 11*name.hashCode() + 13* country.hashCode();
    }
}
