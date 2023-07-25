package DAO;

import javafx.collections.ObservableList;
import model.Customer;

/**
 * This is Customer DAO class
 * @author Alfred Yoo
 */
public interface CustomerDao {

    /**
     * This method returns all customers in the database in list
     * @return allCustomers
     */
    public ObservableList<Customer> getAllCustomers();

    /**
     * This method returns a customer entry under provided customer id
     * @param customerId
     * @return Customer
     */
    public Customer getCustomer(int customerId);

    /**
     * This method returns all customers under provided country id
     * @param countryId
     * @return Customers by country
     */
    public ObservableList<Customer> getCustomersByCountry(int countryId);

    /**
     * This method inserts a new customer to the database with provided attributes
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     * @return row number of inserted entry
     */
    public int addCustomer(String customerName, String address, String postalCode, String phone, int divisionId);

    /**
     * This method updates an existing customer entry with provided attributes
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     * @return row number of updated entry
     */
    public int updateCustomer(int customerId, String customerName, String address, String postalCode, String phone, int divisionId);

    /**
     * This method deletes existing customer entry in the database
     * @param customerId
     * @param customerName
     * @return row number of deleted entry
     */
    public int deleteCustomer(int customerId, String customerName);

    /**
     * This method returns an existing customer entry under provided customer id
     * @param customerId
     * @return customer
     */
    public Customer searchCustomerId(int customerId);

    /**
     * This method returns an existing customer entry under provided customer name
     * @param customerName
     * @return Customer
     */
    ObservableList<Customer> searchCustomerName(String customerName);

}
