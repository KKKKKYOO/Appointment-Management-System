package DAO;

import javafx.collections.ObservableList;
import model.Division;

/**
 * This is Division DAO class
 * @author Alfred Yoo
 */
public interface DivisionDao {

    /**
     * This method return all divisions in the database in list
     * @return allDivision list
     */
    public ObservableList<Division> getAllDivisions();

    /**
     * This method return an existing division entry under provided division id
     * @param divisionId
     * @return division
     */
    public Division getDivision(int divisionId);

    /**
     * This method returns all division under provided country id
     * @param countryId
     * @return divisionByCountry list
     */
    public ObservableList<Division> getDivisionsByCountry(int countryId);

    /**
     * This method updates an existing division entry with provided division name
     * @param currentDivisionName
     * @param countryId
     * @param newDivisionName
     * @return
     */
    public int updateDivisionName(String currentDivisionName, int countryId, String newDivisionName);

    /**
     * This method updates an existing division entry with provided country ID
     * @param divisionName
     * @param currentCountryId
     * @param newCountryId
     * @return row number of updateed entry
     */
    public int updateDivisionCountry(String divisionName, int currentCountryId, int newCountryId);

    /**
     * This method deletes an existing division entry
     * @param divisionId
     * @param divisionName
     * @return row number of deleted entry
     */
    public int deleteDivision(int divisionId, String divisionName);

    /**
     * This method inserts a new division entry with provided attributes
     * @param divisionName
     * @param countryId
     * @return
     */
    public int addDivision(String divisionName, int countryId);
}
