package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import DAO.AppointmentDao;
import DAO.AppointmentDaoConcrete;
import DAO.JDBC;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Optional;
import java.util.ResourceBundle;
import utilities.TimeManager;

/**
 * This is Main Appointment class
 * <p>
 * Upon logging in, this page displays the current list of appointments and
 * enables the user to
 * search, modify, and delete appointments. It also allows filtering of upcoming
 * appointments by week and month.
 * The page offers interface to navigate to different services
 * </p>
 */
public class MainAppt implements Initializable {

    private Stage stage;
    private Parent scene;

    /**
     * This is Menu button
     */
    @FXML
    private MenuButton as;

    /**
     * This is Appointment Schedule label
     */
    @FXML
    private Text asLB;

    /**
     * This is Appointment Schedule Table View
     */
    @FXML
    private TableView<Appointment> asTV;

    /**
     * This is Add Appointment button
     */
    @FXML
    private Button asaddBT;

    /**
     * This is Appointment Delete button
     */
    @FXML
    private Button asdeleteBT;

    /**
     * This is View Appointment Monthly radio button
     */
    @FXML
    private RadioButton asmonthlyRB;

    /**
     * This is Appointment Search date picker
     */
    @FXML
    private DatePicker assearchDP;

    /**
     * This is User Time Zone label
     */
    @FXML
    private Label astimezoneLB;

    /**
     * This is Appointment Schedule Appointment ID column
     */
    @FXML
    private TableColumn<?, ?> astvapptidCOL;

    /**
     * This is Appointment Schedule Customer ID column
     */
    @FXML
    private TableColumn<?, ?> astvcidCOL;

    /**
     * This is Appointment Schedule Contact ID column
     */
    @FXML
    private TableColumn<?, ?> astvcontactCOL;

    /**
     * This is Apopintment Schedule Description column
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
     * This is Appointment Title column
     */
    @FXML
    private TableColumn<?, ?> astvtitleCOL;

    /**
     * This is Appointment Type column
     */
    @FXML
    private TableColumn<?, ?> astvtypeCOL;

    /**
     * This is User ID column
     */
    @FXML
    private TableColumn<?, ?> astvuidCOL;

    /**
     * This is Update Appointment button
     * <p>
     * Upon a click, this button directs you to update appointment screen
     * </p>
     */
    @FXML
    private Button asupdateBT;

    /**
     * This is View All Appointment Toggle
     * <p>
     * Upon toggle, it displays all appointments under current user
     * </p>
     */
    @FXML
    private RadioButton asviewallRB;

    /**
     * This is View Weekly radio button
     */
    @FXML
    private RadioButton asweeklyRB;

    /**
     * This is View Toggle Group
     * <p>
     * Toggling the radio buttons will display filtered appointments
     * </p>
     */
    @FXML
    private ToggleGroup viewTG;

    /**
     * This is onActionWeekly method
     * <p>
     * </p>
     * This method displays all appointments within corresponding week
     * </p>
     *
     * @param actionEvent
     */
    public void onActionViewWeekly(ActionEvent actionEvent) {
        System.out.println("View Weekly Toggled");
        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoConcrete();
        asTV.setItems(apptDao.apptsWeekly(LocalDate.from(DAO.LoginDB.getLoginLDT())));
    }

    /**
     * This is onActionViewMonthly method
     * <p>
     * </p>
     * This method displays all appointments within corresponding month
     * </p>
     *
     * @param actionEvent
     */
    public void onActionViewMonthly(ActionEvent actionEvent) {
        System.out.println("View Monthly Toggled");
        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoConcrete();
        asTV.setItems(apptDao.apptsMonthly(LocalDate.from(DAO.LoginDB.getLoginLDT())));
    }

    /**
     * This is onActionViewAll method
     * <p>
     * This method displays all appointments
     * </p>
     *
     * @param actionEvent
     */
    public void onActionViewAll(ActionEvent actionEvent) {
        System.out.println("View All Toggled");
        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoConcrete();
        asTV.setItems(apptDao.getAllAppointments());
    }

    /**
     * This is onActionSearchApptByDate method
     * <p>
     * This method displays all appointments under selected date
     * </p>
     *
     * @param actionEvent
     */
    public void onActionSearchApptsByDate(ActionEvent actionEvent) {
        JDBC.openConnection();
        AppointmentDao appointmentDao = new AppointmentDaoConcrete();
        asTV.setItems(appointmentDao.getAllAppointments());
        LocalDate selDate = assearchDP.getValue();

        try {
            ObservableList<Appointment> appts = appointmentDao.lookUpAppointment(selDate);
            asTV.setItems(appts);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (!((AppointmentDaoConcrete) appointmentDao).apptFound) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No Appointment was found.");
            alert.showAndWait();
        }
    }

    /**
     * This is onActionAddAppt method
     * <p>
     * This method navigates the user to add appointment screen
     * </p>
     *
     * @param actionEvent
     * @throws IOException
     */
    public void onActionAddAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Appointment");

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppt.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is onActionUpdateAppt method
     * <p>
     * This method navigates the user to update appointment screen with selected
     * appointment
     * </p>
     *
     * @param actionEvent
     * @throws IOException
     */
    public void onActionUpdateAppt(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Appointment Button Clicked!");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateAppt.fxml"));
            Parent scene = loader.load();

            UpdateAppt updateApptController = loader.getController();

            Appointment selectedAppt = asTV.getSelectionModel().getSelectedItem();

            updateApptController.updateAppointment(selectedAppt);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Appointment Selected");
            alert.setContentText("Please select an appointment to update!");
            alert.showAndWait();
        }
    }

    /**
     * This is onActionDeleteAppt method
     * <p>
     * This method deletes selected appointment from the database
     * </p>
     *
     * @param actionEvent
     */
    public void onActionDeleteAppt(ActionEvent actionEvent) {
        System.out.println("Delete Appointment Button Clicked!");

        JDBC.openConnection();
        AppointmentDao apptDao = new AppointmentDaoConcrete();
        Appointment selAppt = asTV.getSelectionModel().getSelectedItem();
        int appointmentId = selAppt.getApptId();
        int customerId = selAppt.getCustomerId();
        String type = selAppt.getType();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("The selected \"Appointment\" will be deleted. Do you wish to continue?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            System.out.println(apptDao.deleteAppointment(appointmentId, customerId, type));

            JDBC.openConnection();
            asTV.setItems(apptDao.getAllAppointments());
        }
    }

    /**
     * This is onActionCustomer method
     * <p>
     * This method navigates the user to Customer screen once Customer button is
     * clicked
     * <p/>
     *
     * @param actionEvent
     * @throws IOException
     */
    public void onActionCustomers(ActionEvent actionEvent) throws IOException {
        System.out.println("Customers Button Clicked!");

        stage = (Stage) as.getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/customerMain.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is onActionReports method
     * <p>
     * This method navigates the user to report sceen once Reports button is clicked
     * </p>
     *
     * @param actionEvent
     * @throws IOException
     */
    public void onActionReports(ActionEvent actionEvent) throws IOException {
        System.out.println("Reports Button Clicked!");

        stage = (Stage) as.getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportContact.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is onAcitonLogOut method
     * This method exits the program once the log out button is clicked
     *
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
     * This is initialize method
     * <p>
     * this method initializes the Main Appointments page by setting the time zone
     * label,
     * defining the columns for appointment information, establishing a database
     * connection,
     * and populating the TableView with appointment data.
     * </p>
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Appointment Schedule (Main Menu): I am initialized!");

        astimezoneLB.setText("Your Time Zone: " + String.valueOf(ZoneId.systemDefault()));

        astvapptidCOL.setCellValueFactory(new PropertyValueFactory<>("apptId"));
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
        AppointmentDao appointmentDao = new AppointmentDaoConcrete();
        asTV.setItems(appointmentDao.getAllAppointments());
    }
}
