package controller;
import DAO.AppointmentDao;
import DAO.AppointmentDaoConcrete;
import DAO.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static DAO.LoginDB.loginQuery;

/**
 * This is Login class
 * @author Alfred Yoo
 */
public class Login implements Initializable{
    Stage stage;
    Parent scene;
    @FXML
    private Label TimeZoneLB;

    @FXML
    private Label countrydivisionLB;

    @FXML
    private Label pwdLB;

    @FXML
    private TextField pwdTF;

    @FXML
    private Button resetBT;

    @FXML
    private Button loginBT;

    @FXML
    private Label usernameLB;

    @FXML
    private TextField usernameTF;

    public void onActionLogIn(ActionEvent actionEvent) {
        System.out.println("Log In Attempted");
        try{
            String userName = usernameTF.getText();
            String pwd = pwdTF.getText();
            User loginStatus = loginQuery(userName, pwd);
            LocalDateTime currTime = LocalDateTime.now();
            String activityLog = "activity_log.txt";

            FileWriter fileWriter = new FileWriter(activityLog, true);
            PrintWriter outputFile = new PrintWriter(fileWriter);

            if (loginStatus != null){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/main.fxml"));
                Parent root = loader.load();
                stage = (Stage) loginBT.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                JDBC.openConnection();
                AppointmentDao appointmentDao = new AppointmentDaoConcrete();
                LocalDateTime loginLocalDateTime = DAO.LoginDB.getLoginLDT();
                appointmentDao.upcomingApptAlert(loginLocalDateTime);
                outputFile.println(userName + ": successful Login at " + currTime + " (" + ZoneId.systemDefault() + ")");
            } else if(Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("bundle/language_fr", Locale.getDefault());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(rb.getString("Invalid") + " " + rb.getString("Username") +
                        " " + rb.getString("and") + "/" + rb.getString("or") +
                        " " + rb.getString("Password") + ". " + rb.getString("Please") +
                        " " + rb.getString("try") + rb.getString("again") + "!");
                alert.showAndWait();

                outputFile.println(userName + " Login attempt at " + currTime + " (" + ZoneId.systemDefault() + ")");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Username and/or Password. Please try again!");
                alert.showAndWait();

                outputFile.println(userName + ": unsuccessful Login at " + currTime + " (" + ZoneId.systemDefault() + ")");
            }
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * This is onActionResetFields method
     * <p>This method resets the username and password field to blank</p>
     * @param actionEvent
     */
    public void onActionResetFields(ActionEvent actionEvent) {
        System.out.println("Reset Button clicked!");
        usernameTF.setText("");
        pwdTF.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Login: I am initialized!");

        countrydivisionLB.setText(String.valueOf(ZoneId.systemDefault()));

        try {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("bundle/language_fr", Locale.getDefault());

                usernameLB.setText(rb.getString("Username"));
                pwdLB.setText(rb.getString("Password"));
                TimeZoneLB.setText(rb.getString("TimeZone"));
                resetBT.setText(rb.getString("Reset"));
                loginBT.setText(rb.getString("Login"));
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
