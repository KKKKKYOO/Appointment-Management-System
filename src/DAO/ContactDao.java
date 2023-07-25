package DAO;

import javafx.collections.ObservableList;
import model.Contact;

/**
 * This is Contact DAO class
 * @author Alfred Yoo
 */
public interface ContactDao {
    /**
     * This method returns all contacts from the database in list
     * @return allContacts
     */
    public ObservableList<Contact> getAllContacts();

    /**
     * This method returns a Contact instance under provided contact ID
     * @param contactId
     * @return cotnact
     */
    public Contact getContact(int contactId);

    /**
     * This method updates existing contact entry with provided attributes
     * @param contactId
     * @param currName
     * @param newName
     * @return row number of the updated entry
     */
    public int updateContact(int contactId, String currName, String newName);

    /**
     * This method deletes existing contact entry with provided contact ID
     * @param contactId
     * @return row number of deleted entry
     */
    public int deleteContact(int contactId);

    /**
     * This method inserts a new contact entry with provided contact name
     * @param contactName
     * @return row number of inserted entry
     */
    public int addContact(String contactName);
}
