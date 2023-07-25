package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

public class CountryDaoConcrete  implements CountryDao{

    /**
     * This is a list containing all countries.
     */
    ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**
     * This method retrieves all entries(countries) from database. Each country is then appended to "allCountries".
     * @return allCountries
     */

    @Override
    public ObservableList<Country> getAllCountries() {
        try{
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int countryId = result.getInt("Country_ID");
                String countryName = result.getString("Country");
                Country country = new Country(countryId, countryName);
                allCountries.add(country);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return allCountries;
    }

    /**
     * This method retrieves a queried entry (Country) from the database
     * <p> If no entry matches the query, nothing happens</p>
     */
    @Override
    public Country getCountry(int countryId) {
        try{
            String sql = "SELECT * FROM countries WHERE Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, countryId);

            ResultSet result = ps.executeQuery();
            Country countryResult = null;
            if(result.next()) {
                countryId = result.getInt("Country_ID");
                String countryName = result.getString("Country");
                countryResult = new Country(countryId, countryName);
            }
            return countryResult;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method inserts a new entry (Country) to the database
     * @return row number of inserted entry
     */
    @Override
    public int addCountry(String countryName) {
        int row = 0;
        try {
            String sql = "INSERT INTO countries (Country) VALUES(?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, countryName);
            row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Country INSERT was successful!");
            } else {
                System.out.println("Country INSERT failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return row;
    }

    /**
     * This method updates an existing entry (Country) to the database
     * <p>If queries entry is not found, nothing happens</p>
     * @return row number of updated entry
     */
    @Override
    public int updateCountry(int countryId, String currentCountryName, String newCountryName) {
        int rowsAffected = 0;
        try {
            String sql = "UPDATE countries SET Country=? WHERE Country=? AND Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newCountryName);
            ps.setString(2, currentCountryName);
            ps.setInt(3, countryId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(currentCountryName + " country name UPDATE was successful!");
                System.out.println("New country name: " + newCountryName);
            } else {
                System.out.println(currentCountryName + " country name UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }

    /**
     * This method deletes an existing entry (Country) in the database
     * <p>If queried entry is not found, nothing happens</p>
     * @return row number of the deleted entry
     */
    @Override
    public int deleteCountry(int countryId, String countryName) {
        int row = 0;
        JDBC.openConnection();
        DivisionDao divisionDao = new DivisionDaoConcrete();
        try{
            if(divisionDao.getDivisionsByCountry(countryId).isEmpty()) {
                try {
                    String sql = "DELETE FROM countries WHERE Country_ID=? AND Country=?";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, countryId);
                    ps.setString(2, countryName);
                    row = ps.executeUpdate();

                    if (row > 0) {
                        System.out.println("Country: " + countryId + " " + countryName + " was successfully deleted!");
                    } else {
                        System.out.println("Country DELETE failed!");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            if(!divisionDao.getDivisionsByCountry(countryId).isEmpty()){
                System.out.println(countryName + " failed to DELETE!");
                System.out.println(countryName + " has associated divisions. DELETE remaining divisions in order to continue.");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return row;
    }
}
