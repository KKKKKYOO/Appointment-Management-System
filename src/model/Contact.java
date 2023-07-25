package model;

/**
 * This is Contact class
 */
public class Contact {
    /**
     * This is Contact ID
     */
    private int contactId;

    /**
     * This is Contact Time;
     */
    private String contactName;

    /**
     * This is Contact constructor
     * @param contactId
     * @param contactName
     */
    public Contact(int contactId, String contactName) {
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /**
     * This method returns contact id
     * @return
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * This method sets contact id
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * This method returns contact name
     * @return
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * This method sets contact name
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String toString(){
        return (Integer.toString(contactId) + " " + getContactName());
    }
}
