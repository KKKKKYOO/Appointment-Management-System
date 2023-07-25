package controller;

import DAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is User Appointment Type class
 * @author Alfred Yoo
 */
public class ReportUserAppt implements Initializable {
    private Stage stage;
    private Parent scene;

    /**
     * This is Back button
     */
    @FXML
    private Button backBT;

    /*
    This is Contact Report button
     */
    @FXML
    private Button contactreportBT;

    /**
     * This is Appointment Description column
     */
    @FXML
    private TableColumn<?, ?> descCOL;

    /**
     * This is Logout button
     */
    @FXML
    private Button logoutBT;

    /**
     * This is Month Type Report button
     */
    @FXML
    private Button monthtypereportBT;

    /**
     * This is Report table view
     */
    @FXML
    private TableView<Appointment> reportTV;

    /**
     * This is Appointment Type column
     */
    @FXML
    private TableColumn<?, ?> typeCOL;

    /**
     * This is User column
     */
    @FXML
    private ComboBox<User> usrCB;

    @FXML
    private Label totalLB;

    /**
     * This is onActionBack method
     * <p>This method navigates the user back to main.fxml</p>
     * @param actionEvent
     * @throws IOException
     */
    public void onActionBack(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is onActionLogout method
     * <p>This method exits the program</p>
     * @param actionEvent
     */
    public void onActionLogout(ActionEvent actionEvent) {
        System.out.println("Logout Button Clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Do you wish to Exit the program?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            System.exit(0);
        }
    }

    /**
     * This is onActionContactReport method
     * <p>This method navigates the user to ReportCotnact.fxml</p>
     * @param actionEvent
     * @throws IOException
     */
    public void onActionContactReport(ActionEvent actionEvent) throws IOException {
        System.out.println("Contact Report Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportContact.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is onActionMonthTypeReport
     * <p>This method navigates the user to ReportMonthType.fxml</p>
     * @param actionEvent
     * @throws IOException
     */
    public void onActionMonthTypeReport(ActionEvent actionEvent) throws IOException {
        System.out.println("Month & Type Report Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MonthTypeReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * This is onActionPopulateTable method
     * <p>This method populates the table with data retrieved from the database</p>
     * @param actionEvent
     */
    public void onActionPopulateTable(ActionEvent actionEvent) {
        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoConcrete();
        int userId = usrCB.getSelectionModel().getSelectedItem().getUserID();
        reportTV.setItems(apptDao.getApptByUser(userId));
        totalLB.setText("Total: " + (reportTV.getItems()).size());

    }

    /**
     * This is initialize method
     * <p>This method sets up the intiial state of the User & Type report page by assigning values to table columns and populting
     * the report table view with data retrieved from the database</p>
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Reports (User Type): I am Initialized!");

        typeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
        descCOL.setCellValueFactory(new PropertyValueFactory<>("desc"));

        JDBC.openConnection();
        UserDao userDao = new UserDaoConcrete();
        usrCB.setItems(userDao.getAllUsers());
    }
}
