package DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static DAO.JDBC.connection;

/**
 * This is the "Division Dao Concrete" class
 * This class implements the "Division Dao" interface
 */
public class DivisionDaoConcrete implements DivisionDao{
    /**
     * List of all divisions
     */
    ObservableList<Division> allDivisions = FXCollections.observableArrayList();
    /**
     * List of all divisions with corresponding countryId
     */
    ObservableList<Division> divisionsByCountry = FXCollections.observableArrayList();

    /**
     * This method retrieves all entries (Divisions) from the database
     * <p>Retrieved entries are appended to "allDivisions"</p>
     * @return allDivisions
     */
    @Override
    public ObservableList<Division> getAllDivisions() {
        try{
            String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID " +
                    "= countries.Country_ID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int divisionId = result.getInt("Division_ID");
                int countryId = result.getInt("Country_ID");
                String divisionName = result.getString("Division");
                String countryName = result.getString("Country");
                Division division = new Division(divisionId, divisionName, countryId, countryName);
                allDivisions.add(division);
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return allDivisions;
    }
    /**
     * This method retrieves a queried entry (Division) from the database
     * <p> If no entry matches the query, nothing happens</p>
     * @return Division instance with corresponding attributes with the queried entry
     */
    @Override
    public Division getDivision(int divisionId){
        try{
            String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID " +
                    "= countries.Country_ID AND Division_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, divisionId);

            ResultSet result = ps.executeQuery();
            Division divisionResult = null;
            if(result.next()) {
                divisionId = result.getInt("Division_ID");
                int countryId = result.getInt("Country_ID");
                String divisionName = result.getString("Division");
                String countryName = result.getString("Country");
                divisionResult = new Division(divisionId, divisionName, countryId, countryName);
            }
            return divisionResult;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * This method retrieves all entries with corresponding countryId attribute
     * @param countryId
     * @return divisionsbyCountry
     */
    @Override
    public ObservableList<Division> getDivisionsByCountry(int countryId) {
        try{
            String sql = "SELECT * FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID " +
                    "= countries.Country_ID AND first_level_divisions.Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, countryId);

            ResultSet result = ps.executeQuery();
            while(result.next()) {
                int divisionId = result.getInt("Division_ID");
                countryId = result.getInt("Country_ID");
                String divisionName = result.getString("Division");
                String countryName = result.getString("Country");
                Division division = new Division(divisionId, divisionName, countryId, countryName);
                divisionsByCountry.add(division);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return divisionsByCountry;
    }

    /**
     * This method inserts a new entry (Division) to the database
     * @return row number of inserted entry
     */
    @Override
    public int addDivision(String divisionName, int countryId) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO first_level_divisions (Division, Country_ID) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, divisionName);
            ps.setInt(2, countryId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Division INSERT was successful!");
            } else {
                System.out.println("Division INSERT failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rowsAffected;
    }


    /**
     * This method updates a queried entry
     * <p> This method queries an entry with corresponding division name and country ID,
     * and then updates the division name attribute of the entry.
     * If no corresponding entry is found, nothing happens.</p>
     * @param countryId
     * @param currentDivisionName
     * @param newDivisionName
     * @return row number of updated entry
     */
    @Override
    public int updateDivisionName(String currentDivisionName, int countryId, String newDivisionName) {
        int row = 0;
        try {
            String sql = "UPDATE first_level_divisions SET Division=? WHERE Division=? AND Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newDivisionName);
            ps.setString(2, currentDivisionName);
            ps.setInt(3, countryId);
            row = ps.executeUpdate();

            if (row > 0) {
                System.out.println(currentDivisionName + " division UPDATE was successful!");
                System.out.println("New division name: " + newDivisionName);
            } else {
                System.out.println(currentDivisionName + " division name UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return row;
    }

    /**
     * This method updates a queried entry
     * <p>Thie method queries an entry with corresponding division name and countryId,
     * and then updates the division name attribute of the entry.
     * If no corresponding entry is found, nothing happens.</p>
     * @param divisionName
     * @param currentCountryId
     * @param newCountryId
     * @return row number of updated query
     */
    @Override
    public int updateDivisionCountry(String divisionName, int currentCountryId, int newCountryId) {
        int row = 0;
        try {
            String sql = "UPDATE first_level_divisions SET Country_ID=? WHERE Division=? AND Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, newCountryId);
            ps.setString(2, divisionName);
            ps.setInt(3, currentCountryId);
            row = ps.executeUpdate();

            if (row > 0) {
                System.out.println(divisionName + " country UPDATE was successful!");
            } else {
                System.out.println(divisionName + " country UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return row;
    }

    /**
     * This method deletes an existing entry (Division) in the database
     * <p>If queried entry is not found, nothing happens</p>
     * @return row number of the deleted entry
     */
    @Override
    public int deleteDivision(int divisionId, String divisionName) {
        int row = 0;
        try {
            String sql = "DELETE FROM first_level_divisions WHERE Division_ID=? AND Division=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, divisionId);
            ps.setString(2, divisionName);
            row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Division: " + divisionId + " " + divisionName + " was successfully deleted!");
            } else {
                System.out.println("Division DELETE failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return row;
    }
}
