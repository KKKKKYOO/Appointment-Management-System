package controller;

import DAO.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import  model.Contact;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is ReportContact class
 * @author Alfred Yoo
 */
public class ReportContact implements Initializable {
    private Stage stage;
    private Parent scene;

    /**
     * This is Appointment Count label
     */
    @FXML
    private Label apptcountLB;

    /**
     * This is Contact label
     */
    @FXML
    private Text contactLB;

    /**
     * This is Appointment Table View
     */
    @FXML
    private TableView<Appointment> asTV;

    /**
     * This is User Time Zone label
     */
    @FXML
    private Label astimezoneLB;

    /**
     * This is Appointment ID column
     */
    @FXML
    private TableColumn<?, ?> astvapptidCOL;

    /**
     * This is Customer ID column
     */
    @FXML
    private TableColumn<?, ?> astvcidCOL;

    /**
     * This is Contact ID column
     */
    @FXML
    private TableColumn<?, ?> astvcontactCOL;

    /**
     * This is Appointment Description column
     */
    @FXML
    private TableColumn<?, ?> astvdescCOL;

    /**
     * This is Appointment End Date column
     */
    @FXML
    private TableColumn<?, ?> astvenddCOL;

    /**
     * This is Appointment End Time column
     */
    @FXML
    private TableColumn<?, ?> astvendtCOL;

    /**
     * This is Appointment Location column
     */
    @FXML
    private TableColumn<?, ?> astvlocationCOL;

    /**
     * This is Appointment Start Date column
     */
    @FXML
    private TableColumn<?, ?> astvstartdCOL;

    /**
     * This is Appointment Start Time column
     */
    @FXML
    private TableColumn<?, ?> astvstarttCOL;

    /**
     * This is Appiontment Title column
     */
    @FXML
    private TableColumn<?, ?> astvtitleCOL;

    /**
     * This is Appointment Type column
     */
    @FXML
    private TableColumn<?, ?> astvtypeCOL;

    /**
     * This is Appointment User ID column
     */
    @FXML
    private TableColumn<?, ?> astvuidCOL;

    /**
     * This is Back button
     */
    @FXML
    private Button backBT;

    /**
     * This is Contact combo box
     */
    @FXML
    private ComboBox<Contact> contactCB;

    /**
     * This is Logout button
     */
    @FXML
    private Button logoutBT;


    /**]
     * This is onActionLogOut method
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
     * This is onActionBack method
     * <p>This method returns the user to main.fxml</p>
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
     * This is onActionUserAppointment method
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
     * This is onActionMonthTypeReport method
     * <p>This method navigates the user to MonthTypeReport.fxml</p>
     * @param actionEvent
     * @throws IOException
     */
    public void onActionMonthTypeReport(ActionEvent actionEvent) throws IOException {
        System.out.println("Month & Type Report Button Clicked!");

        stage = (Stage)this.logoutBT.getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MonthTypeReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is onActionPopulateTable method
     * <p>This method retrieves and displays appointment data for a selected customer contact , and also updates a label with the
     * total number of appointments for that cotnact</p>
     * @param actionEvent
     */
    public void onActionPopulateTable(ActionEvent actionEvent) {
        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoConcrete();
        int contactId = contactCB.getSelectionModel().getSelectedItem().getContactId();
        asTV.setItems(apptDao.getApptByContact(contactId));
        apptcountLB.setText("Total Appointments: " + apptDao.getApptByContact(contactId).size());
    }


    /**
     *  This is initialize method
     *  <p>This method sets up the initial stte of the Contact Report page by assigning values to table columns and populating the
     *  contact combo box with contact data from the database.</p>
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Reports (Contact): I am Initialized!");

        astvapptidCOL.setCellValueFactory(new PropertyValueFactory<>("appId"));
        astvtitleCOL.setCellValueFactory(new PropertyValueFactory<>("title"));
        astvdescCOL.setCellValueFactory(new PropertyValueFactory<>("desc"));
        astvlocationCOL.setCellValueFactory(new PropertyValueFactory<>("location"));
        astvcontactCOL.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        astvtypeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
        astvstartdCOL.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        astvenddCOL.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        astvstarttCOL.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        astvendtCOL.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        astvcidCOL.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        astvuidCOL.setCellValueFactory(new PropertyValueFactory<>("userId"));

        JDBC.openConnection();
        ContactDao contactDao = new ContactDaoConcrete();
        contactCB.setItems(contactDao.getAllContacts());
    }
}
