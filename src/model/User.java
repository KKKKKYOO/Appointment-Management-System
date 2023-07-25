package model;
import java.time.*;
import java.sql.Timestamp;

/**
 * This is User class
 */
public class User {

    /**
     * This is user ID
     */
    private int userId;

    /**
     * This is the username
     */
    private String userName;

    /**
     * This is the user password
     */
    private String passWord;

    /**
     * This is user constructor
     * @param userId
     * @param userName
     * @param passWord
     */
    public User(int userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    /**
     * This method returns userID
     * @return
     */
    public int getUserID() {
        return userId;
    }

    /**
     * This method sets userID
     * @param userID
     */
    public void setUserID(int userID) {
        this.userId = userID;
    }

    /**
     * This method returns username
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method sets username
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method returns user password
     * @return passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * This method sets user password
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * This method returns user ID and username in String
     * @return
     */
    @Override
    public String toString() {
        return ("[" + Integer.toString(userId) + "] " + userName);
    }
}
