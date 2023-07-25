package DAO;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static DAO.JDBC.connection;

/**
 * This is Login Database class
 * @author Alfred Yoo
 */
public class LoginDB {
    /**
     * This is loginQuery method
     * <p>This method authenticates user through querying existing user with provided credentials</p>
     * @param userName
     * @param pwd
     * @return
     */
    public static User loginQuery(String userName, String pwd){
        try{
            String sql = "SELECT * FROM users WHERE User_Name=? AND Password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, pwd);

            ResultSet result = ps.executeQuery();
            User userResult = null;
            if (result.next()) {
                int userId = result.getInt("User_ID");
                userName = result.getString("User_Name");
                pwd = result.getString("Password");
                userResult = new User(userId, userName, pwd);
            }
            return userResult;
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    /**
     * This is getLoginLDT method
     * This method displays the region the user is in on the login screen.
     * @return ldt
     */
    public static LocalDateTime getLoginLDT(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.systemDefault());
        return ldt;
    }
}

