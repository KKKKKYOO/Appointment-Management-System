package DAO;

import javafx.collections.ObservableList;
import model.User;

/**
 * This is User DAO class
 * @author Alfred Yoo
 */
public interface UserDao {

    /**
     * This method return all users in the database in list
     * @return allUsers list
     */
    public ObservableList<User> getAllUsers();

    /**
     * This method returns an existing user entry under provided user id
     * @param userId
     * @return user
     */
    public User getUser(int userId);

    /**
     * Thie method inserts a new user entry with provided attributes
     * @param userName
     * @param password
     * @return row number of inserted entry
     */
    public int addUser(String userName, String password);

    /**
     * This method updates an existing user's username with provided value
     * @param currentUserName
     * @param newUserName
     * @param password
     * @return row number of updated entry
     */
    public int updateUserName(String currentUserName, String newUserName, String password);

    /**
     * This method updates an existing user's password with provided value
     * @param userName
     * @param newPassword
     * @param currentPassword
     * @return row number of updated entry
     */
    public int updateUserPass(String userName, String newPassword, String currentPassword);

    /**
     * This method deletes an existing user entry
     * @param userId
     * @return row number of deleted entry
     */
    public int deleteUser(int userId);
}
