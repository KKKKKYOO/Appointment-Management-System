package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.JDBC.connection;

/**
 * This is the "Contact DAO Concrete" class.
 * This class implements the "Contact Dao" interface.
 *
 * @author Alfred Too
 */
public class ContactDaoConcrete implements ContactDao {

    /**
     * This is a list of all contacts
     */
    ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    /**
     * This is getAllContacts method
     * <p>This method accesses the database and retrieves all contacts. Each contact is then appended to an observable list, "allContacts".</p>
     *
     * @return allContacts ObservableList
     */
    @Override
    public ObservableList<Contact> getAllContacts() {
        try {
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int contactId = result.getInt("Contact_ID");
                String contactName = result.getString("Contact_Name");
                Contact contact = new Contact(contactId, contactName);
                allContacts.add(contact);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            ;
        }
        return allContacts;
    }

    /**
     * This is getContact method
     * <p>This method searches the databae for a specific contact by their unqiue contact ID.</p<
     *
     * @param contactId
     * @return newly created Contact instance with requested contact attributes.
     */
    @Override
    public Contact getContact(int contactId) {
        try {
            String sql = "SELECT * FROM contacts WHERE Contact_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contactId);

            ResultSet result = ps.executeQuery();
            Contact contactResult = null;
            if (result.next()) {
                contactId = result.getInt("Contact_ID");
                String contactName = result.getString("Contact_Name");
                contactResult = new Contact(contactId, contactName);
            }
            return contactResult;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * This is updateCotnact method
     * <p>This method searches the database for a specific contact by their contact ID and contact name, and the updates the corresponding item.</p>
     * @param contactId
     * @param currContactName
     * @param newContactName
     * @return the row number of updated contact
     */
    @Override
    public int updateContact(int contactId, String currContactName, String newContactName) {
        int row= 0;
        try {
            String sql = "UPDATE contacts SET Contact_Name=? WHERE Contact_Name=? AND Contact_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newContactName);
            ps.setString(2, currContactName);
            ps.setInt(3, contactId);
            row = ps.executeUpdate();

            if (row > 0) {
                System.out.println(currContactName + " name UPDATE was successful!");
                System.out.println("New username: " + newContactName);
            } else {
                System.out.println(currContactName + " name UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return row;
    }

    /**
     * This is deleteContact method
     * <p><This method accesses the database and deletes a contact with the specified contact ID when a match is made.</p>
     * @return the row number of deleted contact
     */
    @Override
    public int deleteContact(int contactId) {
        int row = 0;
        try {
            String sql = "DELETE FROM contacts WHERE Contact_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contactId);
            row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Contact [" + contactId + "] delete");
            } else {
                System.out.println("Failed to delete Contact: Queried contact does not exist");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return row;
    }

    /***
     * This is addContact method
     * <p>This method accesses the database and adds a new contact with the desired contact name</p>
     * @return the row number of added contact
     */

    @Override
    public int addContact(String contactName) {
        int row = 0;
        try {
            String sql = "INSERT INTO contacts (Contact_Name) VALUES(?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, contactName);
            row = ps.executeUpdate();

            if (row > 0) {
                System.out.println("Contact inserted!");
            } else {
                System.out.println("Failed to insert contact!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return row;
    }
}


