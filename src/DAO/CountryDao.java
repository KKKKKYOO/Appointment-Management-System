package DAO;

import javafx.collections.ObservableList;
import model.Country;


/**
 * This is Country DAO class
 * @author Alfred Yoo
 */
public interface CountryDao {
    /**
     * This method returns all countries in list
     * @return allCountries
     */
    public ObservableList<Country> getAllCountries();

    /**
     * This method returns Country entry under provided country ID
     * @param countryId
     * @return Country
     */
    public Country getCountry(int countryId);

    /**
     * This method updates an existing country entry under provided country id with provided attributes
     * @param countryId
     * @param currentCountryName
     * @param newCountryName
     * @return
     */
    public int updateCountry(int countryId, String currentCountryName, String newCountryName);

    /**
     * This method deletes a selected country from the database only if its associated divisions list is empty.
     * <p>The methods calls getDivisionsByCountry() with a specific country ID. If the return list is not null, no further action is taken.</p>
     * <p>If the filtered divisions list is empty, indicating no associated diviosns, the method accesses the database and looks for country </p>
     * with the corresponding country ID and country name. It then proceeds to delete that specific country from the database.
     * @param countryId
     * @param countryName
     * @return row number of deleted country
     */
    public int deleteCountry(int countryId, String countryName);

    /**
     * This method inserts a new Country entry to the database
     * @param countryName
     * @return row number of inserted country
     */
    public int addCountry(String countryName);
}
