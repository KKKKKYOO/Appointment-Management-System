package model;
import java.time.*;
import java.sql.Timestamp;

/**
 * This is Country class
 */
public class Country {
    /**
     * This is country id
     */
    private int countryID;
    /**
     * This is country name
     */
    private String name;

    /**
     * This is the Country constructor.
     *
     * @param countryID country ID
     * @param name country name
     * */
    public Country(int countryID, String name) {
        this.countryID = countryID;
        this.name = name;
    }

    /**
     * This method returns country id
     * @return countryId;
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This method sets country id
     * @param countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * This method returns country name
     * @return country name
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets country name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * This is the toString method
     * @return
     */
    @Override
    public String toString(){
        return (getCountryID() + " " + getName());
    }
}
