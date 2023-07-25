package controller;

import DAO.JDBC;
import DAO.ReportDao;
import DAO.ReportDaoConcrete;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Report;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is Report Month Type class
 * @author Alfred Yoo
 */
public class ReportMonthType implements Initializable{

    private Stage stage;
    private Parent scene;

    /**
     * This is Logout button
     */
    @FXML
    private Button lo;

    /**
     * This is Appontment table view
     */
    @FXML
    private TableView<Report> mtTV;

    /**
     * This is Contact Report button
     */
    @FXML
    private Button mtcontactreportBT;

    /**
     * This is Count column
     */
    @FXML
    private TableColumn<?, ?> mtcountCOL;

    /**
     * This is User Appointment Type Report button
     */
    @FXML
    private Button mtuserappttypereportBT;

    /**
     * This is Month column
     */
    @FXML
    private TableColumn<?, ?> mtmonthCOL;

    /**
     * This is Back button
     */
    @FXML
    private Button mtreturnBT;

    /**
     * This is Appointment Type column
     */
    @FXML
    private TableColumn<?, ?> mttypeCOL;

    @FXML
    private ComboBox<?> monthCB;

    @FXML
    private Label totalLB;

    /**
     * This is onActionBack method
     * <p>This method navigates the user to main.fxml</p>
     * @param actionEvent
     * @throws IOException
     * @throws IOException
     */
    public void onActionBack(ActionEvent actionEvent) throws IOException, IOException {
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
     * <p>This method navigates the user to ReportsContact.fxml</p>
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
     * This is onActionUserAppointmentTypeReport method
     * <p>This method navigates the user to ReportUserAppt.fxml</p>
     * @param actionEvent
     * @throws IOException
     */
    public void onActionUserAppoitnmentTypeReport(ActionEvent actionEvent) throws IOException, IOException {
        System.out.println("User Appointment Type Report Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportUserAppt.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is initialize method
     * This method sets up the initial state of the Month & Type report page by assigning values to table columns and populating
     * the report table view with data retrieved from the database
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Reports (Month & Type): I am Initialized!");

        mtmonthCOL.setCellValueFactory(new PropertyValueFactory<>("month"));
        mttypeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
        mtcountCOL.setCellValueFactory(new PropertyValueFactory<>("count"));


        JDBC.openConnection();;
        ReportDao reportDao = new ReportDaoConcrete();
        mtTV.setItems(reportDao.getAllReports());
        totalLB.setText("Total: " + (mtTV.getItems()).size());
    }
}
