package DAO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static DAO.JDBC.connection;

/**
 * This is Customer DAO Concrete class
 * @author Alfred Yoo
 */
public class CustomerDaoConcrete implements CustomerDao{

    /**
     * List of all customers
     */
    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    public boolean customerFound;

    /**
     * This is getAllCustomers method
     * <p>This method retrieves all entries (Customers) from the database.
     * Each customer is then appended to "allCustomers"</p>
     * @return allCustomers
     */
    @Override
    public ObservableList<Customer> getAllCustomers() {
        try{
            String sql = "SELECT * FROM customers, first_level_divisions, countries WHERE " +
                    "customers.Division_ID = first_level_divisions.Division_ID AND " +
                    "first_level_divisions.Country_ID = countries.Country_ID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int customerId = result.getInt("Customer_ID");
                int divisionId = result.getInt("Division_ID");;
                int countryId = result.getInt("Country_ID");
                String customerName = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postalCode = result.getString("Postal_Code");
                String cell = result.getString("Phone");
                String countryName = result.getString("Country");
                String divisionName = result.getString("Division");
                Customer customer = new Customer(customerId, customerName, address, postalCode, cell, countryName, countryId,
                        divisionName, divisionId);
                allCustomers.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return allCustomers;
    }

    /**
     * This is getCustomer methdo
     * <p>This method retrieves a queried entry (Customer) from the database</p>
     * <p> If no entry matches the query, nothing happens</p>
     * @param customerId
     * @return Customer instance with corresponding attributes with the queried entry
     */
    @Override
    public Customer getCustomer(int customerId) {
        try{
            String sql = "SELECT * FROM customers, first_level_divisions, countries WHERE " +
                    "customers.Division_ID = first_level_divisions.Division_ID AND " +
                    "first_level_divisions.Country_ID = countries.Country_ID AND " +
                    "Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);

            ResultSet result = ps.executeQuery();
            Customer customerResult = null;
            if(result.next()) {
                customerId = result.getInt("Customer_ID");
                int divisionId = result.getInt("Division_ID");;
                int countryId = result.getInt("Country_ID");
                String customerName = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postalCode = result.getString("Postal_Code");
                String cell = result.getString("Phone");
                String countryName = result.getString("Country");
                String divisionName = result.getString("Division");
                customerResult = new Customer(customerId, customerName, address, postalCode, cell, countryName, countryId,
                        divisionName, divisionId);
            }
            return customerResult;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
        return null;
    }

    /**
     * This is getCustomersByCountry method
     * <p>This method retrieves a list of entries (Customers) with corresponding countryID</p>
     * @param countryId
     * @return "customersByCountry"
     */
    @Override
    public ObservableList<Customer> getCustomersByCountry(int countryId) {
        ObservableList<Customer> customersByCountry = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM customers, first_level_divisions, countries WHERE\n" +
                    "customers.Division_ID = first_level_divisions.Division_ID AND \n" +
                    "first_level_divisions.Country_ID = countries.Country_ID AND countries.Country_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, countryId);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int customerId = result.getInt("Customer_ID");
                int divisionId = result.getInt("Division_ID");;
                countryId = result.getInt("Country_ID");
                String customerName = result.getString("Customer_Name");
                String address = result.getString("Address");
                String postalCode = result.getString("Postal_Code");
                String cell = result.getString("Phone");
                String countryName = result.getString("Country");
                String divisionName = result.getString("Division");
                Customer customer = new Customer(customerId, customerName, address, postalCode, cell, countryName, countryId,
                        divisionName, divisionId);
                customersByCountry.add(customer);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return customersByCountry;
    }

    /**
     * This os searchCustomerId method
     * <p>This method retrieves the entry(Customer) with corresponding customerId attribute.</p<
     * @return customer instance with corresponding attributes
     */
    @Override
    public Customer searchCustomerId(int customerId) {
        customerFound = false;
        for(Customer customer : allCustomers){
            if(customer.getCustomerID() == customerId){
                customerFound = true;
                return customer;
            }
        }
        return null;
    }

    /**
     * This is searchCustomerName method
     * <p>This method retrives the entry(Customer) with corresponding customerName attribute</p>
     * @param customerName
     * @return customer instance with corresponding attributes
     */
    @Override
    public ObservableList<Customer> searchCustomerName(String customerName) {
        ObservableList<Customer> filteredCustomers = FXCollections.observableArrayList();
        customerFound = false;

        for(Customer customer : allCustomers) {
            if(customer.getName().toLowerCase().contains(customerName.toLowerCase())){
                filteredCustomers.add(customer);
            }
        }
        if(filteredCustomers.isEmpty()) {
            return allCustomers;
        }
        customerFound = true;
        return filteredCustomers;
    }

    /**
     * This is addCustomer method
     * <p>This method inserts a new entry(Customer) to database with corresponding attributes</p>
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     * @return row number of inserted entry
     */
    @Override
    public int addCustomer(String customerName, String address, String postalCode, String phone, int divisionId) {
        int row = 0;
        try {
            String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                    "VALUES(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);
            row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Customer INSERT was successful!");
            }
            else {
                System.out.println("Customer INSERT failed!");
            }
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return row;
    }

    /**
     * This is updateCustomer method
     * <p>This method updates a queried entry (Customer) with given parameter values.</P>
     *
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     * @return row number of updated entry
     */
    @Override
    public int updateCustomer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId) {
        int rowsAffected = 0;
        try{
            String sql = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=? WHERE Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionId);
            ps.setInt(6, customerId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer UPDATE was successful!");
            } else {
                System.out.println("Customer UPDATE Failed!");
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return rowsAffected;
    }

    /**
     * This is deleteCustomer method
     * <p>This method deleted queried entry with corresponding customerId and customerName attribute.</p>
     * <p>Presents alert regarding to deletion status</p>
     * @param customerId
     * @param customerName
     * @return row number of deleted entry
     */
    @Override
    public int deleteCustomer(int customerId, String customerName) {
        int row = 0;
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID=? AND Customer_Name=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setString(2, customerName);
            row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Customer: [" + customerId + "] " + customerName + " was successfully deleted!");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer DELETE");
                alert.setContentText("[" + customerId + "] " + customerName + " was successfully deleted.");
                alert.showAndWait();
            } else {
                System.out.println("Customer DELETE failed!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer DELETE");
                alert.setContentText("[" + customerId + "] " + customerName + " failed to deleted.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return row;
    }
}
