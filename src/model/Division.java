package model;
import java.sql.Timestamp;

/**
 * This is Division class
 */
public class Division {
    /**
     * first level division ID
     */
    private int divisionId;

    /**
     * This is division name
     */
    private String division;

    /**
     * This is countryID
     */
    private int countryId;

    /**
     * This is country name
     */
    private String countryName;

    /**
     * This is Division constructor
     * @param divisionId
     * @param division
     * @param countryId
     * @param countryName
     */
    public Division(int divisionId, String division, int countryId, String countryName) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * This method returns Division ID
     * @return divisionId
     */

    public int getDivisionId() {
        return divisionId;
    }

    /**
     * This method sets division id
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * This method returns Division name
     * @return division;
     */
    public String getDivision() {
        return division;
    }

    /**
     * This method sets Division name
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * This method returns Country ID
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * This method sets Country Id
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * This method return Country name
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * This method sets Country name
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return(division);
    }
}
