package controller;
import DAO.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.*;
import utilities.TimeManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is Update Appointment class
 * <p>This class is a controller for UpdateAppointment.fxml</p>
 * @author Alfred Yoo
 */
public class UpdateAppt implements Initializable {
    private Stage stage;
    private Parent scene;

    /**
     * This is Cancel button
     */
    @FXML
    private Button apptcancelBT;

    /**
     * This is Contact combo box
     */
    @FXML
    private ComboBox<Contact> apptcontactCB;

    /**
     * This is Customer combo box
     */
    @FXML
    private ComboBox<Customer> apptcustidCB;

    /**
     * This is Appointment Description text field
     */
    @FXML
    private TextField apptdescTF;

    /**
     * This is Appointment End date picker
     */
    @FXML
    private DatePicker apptenddateDP;

    /**
     * This is Appointment End Time combo box
     */
    @FXML
    private ComboBox<LocalTime> apptendtimeCB;

    /**
     * This is Appointment ID label
     */
    @FXML
    private TextField apptidLB;

    /**
     * This is Appointment Location text field
     */
    @FXML
    private TextField apptlocationTF;

    /**
     * This is Save button
     */
    @FXML
    private Button apptsaveBT;

    /**
     * This is Appointment Start date picker
     */
    @FXML
    private DatePicker apptstartdateDP;

    /**
     * This is Appointment Start Time combo box
     */
    @FXML
    private ComboBox<LocalTime> apptstarttimeCB;

    /**
     * This is Appointment Title TextField
     */
    @FXML
    private TextField appttitleTF;

    /**
     * This is Appointment Type text field
     */
    @FXML
    private TextField appttypeTF;

    /**
     * This is User combo box
     */
    @FXML
    private ComboBox<User> apptusridCB;

    /**
     * This is Customer ID
     */
    public int custId;
    /**
     * This is User ID
     */
    public int usrId;
    /**
     * This is Contact ID
     */
    public int contactId;
    /**
     * This is Appointment Type
     */
    public String type;
    /**
     * This is Appointment Location
     */
    public String location;
    /**
     * This is Appointment Title
     */
    public String title;
    /**
     * This is Appointment Description
     */
    public String desc;
    /**
     * This is Appointment Start Date
     */
    public LocalDate startDate;
    /**
     * This is Appointment End Date
     */
    public LocalDate endDate;
    /**
     * This is Appointment Start Time
     */
    public LocalTime startTime;
    /**
     * This is Appointment End Time
     */
    public LocalTime endTime;
    /**
     * This is Appointment Start Date Time
     */
    public LocalDateTime startDateTime;
    /**
     * This is Appointment End Date Time
     */
    public LocalDateTime endDateTime;

    /**
     * This is selected Appointment entry
     */
    Appointment selectedAppt = null;


    /**
     * This is updateAppointment method
     * <p>This method populates the attributes with selected appointment's associated values</p>
     * @param selectedAppt
     */
    public void updateAppointment(Appointment selectedAppt) {
        JDBC.openConnection();
        ContactDao contactDao = new ContactDaoConcrete();
        CustomerDao customerDao = new CustomerDaoConcrete();
        UserDao userDao = new UserDaoConcrete();

        this.selectedAppt = selectedAppt;
        appttitleTF.setText(String.valueOf(selectedAppt.getTitle()));
        apptdescTF.setText(String.valueOf(selectedAppt.getDesc()));
        apptlocationTF.setText(String.valueOf(selectedAppt.getLocation()));
        appttypeTF.setText(String.valueOf(selectedAppt.getType()));

        Contact selContact = null;
        for(Contact contact : contactDao.getAllContacts()) {
            if(contact.getContactId() == selectedAppt.getContactId()) {
                selContact = contact;
                break;
            }
        }
        apptcontactCB.getSelectionModel().select(selContact);

        Customer selCustomer = null;
        for(Customer customer : customerDao.getAllCustomers()) {
            if(customer.getCustomerID() == selectedAppt.getCustomerId()){
                selCustomer = customer;
                break;
            }
        }
        apptcustidCB.getSelectionModel().select(selCustomer);

        User selUser = null;
        for(User user : userDao.getAllUsers()) {
            if(user.getUserID() == selectedAppt.getUserId()) {
                selUser = user;
                break;
            }
        }
        apptusridCB.getSelectionModel().select(selUser);

        apptstartdateDP.setValue(selectedAppt.getStartDate());
        apptenddateDP.setValue(selectedAppt.getEndDate());
        apptstarttimeCB.getSelectionModel().select(selectedAppt.getStartTime());
        apptendtimeCB.getSelectionModel().select(selectedAppt.getEndTime());
    }

    /**
     * This in onActionSave method
     * <p>This method handles the valiudation and updating of an appointment. It performs various checks,
     * displays error messages if necessary, and updates the appointment in the database if all conditions are met</p>
     * @param actionEvent
     */
    public void onActionSave(ActionEvent actionEvent) {
        System.out.println("Save Button Clicked");

        try {
            boolean saveState = false;
            type = appttypeTF.getText();
            title = appttitleTF.getText();
            location = apptlocationTF.getText();
            contactId = apptcontactCB.getSelectionModel().getSelectedItem().getContactId();
            startDate = apptstartdateDP.getValue();
            endDate = apptenddateDP.getValue();
            startTime = apptstarttimeCB.getSelectionModel().getSelectedItem();
            endTime = apptendtimeCB.getSelectionModel().getSelectedItem();
            custId = apptcustidCB.getSelectionModel().getSelectedItem().getCustomerID();
            usrId = apptusridCB.getSelectionModel().getSelectedItem().getUserID();
            desc=apptdescTF.getText();
            startDateTime=LocalDateTime.of(startDate,startTime);
            endDateTime=LocalDateTime.of(endDate,endTime);
            requiredFieldCheck(title, type, location, desc);

            AppointmentDao apptDao = new AppointmentDaoConcrete();

            boolean flag1=logicalCheckDT(apptDao);
            boolean flag2=logicalCheckSTET(apptDao);
            boolean flag3=logicalCheckOverlap(apptDao);
            if(!flag1 && !flag2 && !flag3)
                apptDao.updateAppointment(selectedAppt.getApptId(), custId, usrId, contactId, title, desc, location, type, startDateTime, endDateTime);


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This is requiredFieldCheck method
     * <p>This method checks for blank fields and displays warning message if any blank field is found</p>
     * @param title
     * @param type
     * @param location
     * @param desc
     */
    public void requiredFieldCheck(String title, String type, String location, String desc){

        List<String> emptyFields = new ArrayList<>();

        if (title.isBlank()) {
            emptyFields.add("Title");
        }
        if (desc.isBlank()) {
            emptyFields.add("Description");
        }
        if (location.isBlank()) {
            emptyFields.add("Location");
        }
        if (type.isBlank()) {
            emptyFields.add("Type");
        }

        if (!emptyFields.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning: Fields cannot be left blank");
            alert.setHeaderText(null);
            alert.setContentText("The following fields cannot be left blank:\n" + String.join("\n", emptyFields));
            alert.showAndWait();
        }
    }

    /**
     * This is logicalCheckDT method
     * <p>This is method checks for any logical errors regarding business hours</p>
     * @param apptDao
     */
    public boolean logicalCheckDT(AppointmentDao apptDao){
        if(apptDao.checkApptStartTime(startDateTime) && apptDao.checkApptEndTime(endDateTime)){
            return false;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Start or End Time");
            alert.setContentText("Select a time that is within the business hours.\nBusiness Hours: 08:00 - 22:00 EST");
            alert.showAndWait();
            return true;
        }
    }

    /**
     * This is logicalCheckSTET method
     * <p>This method checks for any logical errors regarding appointment date time</p>
     * @param apptDao
     */
    public boolean logicalCheckSTET(AppointmentDao apptDao){
        if (startDateTime.toLocalTime().isBefore(endDateTime.toLocalTime())) {
            return false;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Start or End Time");
            alert.setContentText("\"Start Date/Time\" must come BEFORE \"End Date/Time\".");
            alert.showAndWait();
            return true;
        }
    }

    /**
     * This is logicalCheckOverlap method
     * <p>This method checks for any overlapping appintments</p>
     * @param apptDao
     */
    public boolean logicalCheckOverlap(AppointmentDao apptDao){
        ObservableList<Appointment> g=apptDao.getApptByCustomer(custId);
        boolean flag=false;
        for(Appointment a:g){
            if(startDateTime.isAfter(a.getStartDateTime()) && startDateTime.isBefore(a.getStartDateTime())){
                flag=true;
            }
            else if(endDateTime.isAfter(a.getStartDateTime()) && endDateTime.isBefore(a.getStartDateTime())){
                flag=true;
            }
            else if(startDateTime.isBefore(a.getStartDateTime()) && endDateTime.isAfter(a.getEndDateTime())){
                flag=true;
            }
            else if(startDateTime.isEqual(a.getStartDateTime()) || endDateTime.isEqual(a.getEndDateTime())){
                flag=true;
            }
        }
        if(flag)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Overlapping Appointment");
            alert.setContentText("Customer has overlapping appointments. Please select a different time!");
            alert.showAndWait();
            return true;
        }
        return false;
    }
    /**
     * This is Cancel method
     * @param actionEvent
     * @throws IOException
     */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel \"Add Appointment\"");
        alert.setContentText("All changes will be forgotten, do you wish to continue?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            JDBC.closeConnection();
        }
    }


    /**
     * This is initialize method
     * <p>This method set up the initial state of the UpdateAppointment screen.
     * It assigns values to variables used for displaying business hours,
     * retrieves data from the database to populate various combo boxes and text fields,
     * and converts the time values to the local time zone</p>
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Update Appointment: I am initialized!");
        try {
            ZoneId osZId = ZoneId.systemDefault();
            ZoneId businessZId = ZoneId.of("America/New_York");
            LocalTime startTime = LocalTime.of(8, 0);
            int workHours = 13;

            JDBC.openConnection();
            ContactDao contactDao = new ContactDaoConcrete();
            CustomerDao customerDao = new CustomerDaoConcrete();
            UserDao userDao = new UserDaoConcrete();

            apptcontactCB.setItems(contactDao.getAllContacts());
            apptcustidCB.setItems(customerDao.getAllCustomers());
            apptusridCB.setItems(userDao.getAllUsers());
            apptstarttimeCB.setItems(TimeManager.dynamicBusinessHoursInit(osZId, businessZId, startTime, workHours));
            apptendtimeCB.setItems(TimeManager.dynamicBusinessHoursInit(osZId, businessZId, LocalTime.of(9, 0), workHours));
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
