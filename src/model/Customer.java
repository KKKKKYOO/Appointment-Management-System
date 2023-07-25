package model;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This is Customer class
 */
public class Customer {
    /**
     * This is customer id
     */
    private int customerID;
    /**
     * This is customer name
     */
    private String name;
    /**
     * This is customer address
     */
    private String address;
    /**
     * This is customer postal code
     */
    private String postalCode;
    /**
     * This is customer cell number
     */
    private String cell;
    /**
     * This is customer coutnry
     */
    private String country;
    /**
     * This is customer division
     */
    private String division;

    /**
     * This is customer country id
     */
    private int countryId;

    /**
     * This customer division id
     */
    private int divisionId;

    /**
     * This is Customer constructor
     * @param customerID
     * @param name
     * @param address
     * @param postalCode
     * @param cell
     * @param country
     * @param countryId
     * @param division
     * @param divisionId
     */
    public Customer(int customerID, String name, String address, String postalCode, String cell, String country,
                    int countryId, String division, int divisionId) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.cell = cell;
        this.country = country;
        this.division = division;
        this.countryId = countryId;
        this.divisionId = divisionId;
    }

    /**
     * This method returns customer id
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This method sets customer id
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * This method returns customer name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method set customer name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method return customer address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method set customer address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method return customer postal code
     * @return postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This method set customer postal code
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * This method return customer cell number
     * @return cell
     */
    public String getCell() {
        return cell;
    }

    /**
     * This method set customer cell number
     * @param cell
     */
    public void setCell(String cell) {
        this.cell = cell;
    }

    /**
     * This method return customer country name
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method set customer country name
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method return division name
     * @return division
     */
    public String getDivision() {
        return division;
    }

    /**
     * This method return country id
     * @return country id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * This method set country id
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * This country return division id
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * This method set division id
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * This method set division name
     * @param division
     */
    public void setDivision(String division) {
        this.division = division;
    }


    @Override
    public String toString(){
        return (Integer.toString(getCustomerID()) + getName());
    }
}
