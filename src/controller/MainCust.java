package controller;

import DAO.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is Main Customer class
 * <p>This is a controller for customerMain.fxml</p>
 * @author Alfred Yoo
 */
public class MainCust implements Initializable {

    private Stage stage;
    private Parent scene;

    /**
     * This is Appointment menu item
     */
    @FXML
    private MenuItem apptMI;

    /**
     * This is Customer table view
     */
    @FXML
    private TableView<Customer> custTV;

    /**
     * This is Add Customer button
     */
    @FXML
    private Button custaddBT;

    /**
     * This is Customer Address column
     */
    @FXML
    private TableColumn<?, ?> custaddressCOL;

    /**
     * This is Customer Cell Number column
     */
    @FXML
    private TableColumn<?, ?> custcellCOL;

    /**
     * This is Delete Customer Button
     */
    @FXML
    private Button custdeleteBT;

    /**
     * This is Customer ID column
     */
    @FXML
    private TableColumn<?, ?> custidCOL;

    /**
     * This is Customer menu item
     */
    @FXML
    private MenuButton custmenu;

    /**
     * This is Customer Name column
     */
    @FXML
    private TableColumn<?, ?> custnameCOL;

    /**
     * This is Customer Postal Code column
     */
    @FXML
    private TableColumn<?, ?> custpostalCOL;

    /**
     * This is Customer Search button
     */
    @FXML
    private Button custsearchBT;

    /**
     * This is Customer Search text field
     */
    @FXML
    private TextField custsearchTF;

    /**
     * This is Customer State/Province column
     */
    @FXML
    private TableColumn<?, ?> custstateprovinceCOL;

    /**
     * This is Update Customer button
     */
    @FXML
    private Button custupdateBT;

    /**
     * This is LogOUt button
     */
    @FXML
    private MenuItem logoutMI;

    /**
     * This is Report menu item
     */
    @FXML
    private MenuItem reportMI;

    /**
     * This is User Time Zone label
     */
    @FXML
    private Label usertzLB;

    /**
     * This is onActionSearchCustomer method
     * <p>This method populates the table with customer results based on either Customer ID or Customer Name.
     * It opens a database connection, retrieves all customers, and sets the table view to display customers.</p>
     * @param actionEvent
     */
    public void onActionSearchCustomer(ActionEvent actionEvent) {
        JDBC.openConnection();
        CustomerDao customerDao = new CustomerDaoConcrete();
        custTV.setItems(customerDao.getAllCustomers());

        try{
            int customerId = Integer.parseInt(custsearchTF.getText());
            Customer customer = customerDao.searchCustomerId(customerId);
            custTV.getSelectionModel().select(customer);
            custTV.scrollTo(customer);
            custTV.requestFocus();
        }catch(NumberFormatException e) {
            String customerName = custsearchTF.getText();
            ObservableList<Customer> customers = customerDao.searchCustomerName(customerName);
            custTV.setItems(customers);
        }
        if(!((CustomerDaoConcrete) customerDao).customerFound){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No specified customer was found.");
            alert.showAndWait();
        }
    }

    /**
     * This is onActionAddCustomer method
     * <p>This method navigates the user to AddCustomer.fxml</p>
     * @param actionEvent
     * @throws IOException
     */
    public void onActionAddCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Add Customer Button Clicked!");

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This is onActionupdateCustomer method
     * <p>This method navigates the user to UpdateCustomer.fxml</p>
     * <p>This method expects the user to choose a customer from the table before navigating</p>
     * @param actionEvent
     * @throws IOException
     */
    public void onActionUpdateCustomer(ActionEvent actionEvent) throws IOException {
        System.out.println("Update Customer Button Clicked!");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateCustomer.fxml"));
            Parent scene = loader.load();

            UpdateCustomer updateCustomerController = loader.getController();

            Customer selectedCustomer = custTV.getSelectionModel().getSelectedItem();

            updateCustomerController.updateCustomer(selectedCustomer);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(scene));
            stage.show();
        }catch (RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Customer Selected");
            alert.setContentText("Please select a customer to update!");
            alert.showAndWait();
        }
    }

    /**
     * This is onActionDeleteCustomer method
     * <p>This method deletes a selected customer entry from the database</p>
     * <p>This method displays a confirmation message to ensure safe deletion of the entry</p>
     * @param actionEvent
     */
    public void onActionDeleteCustomer(ActionEvent actionEvent) {
        System.out.println("Delete Customer Button Clicked!");

        JDBC.openConnection();
        CustomerDao customerDao = new CustomerDaoConcrete();
        Customer selectedCustomer = custTV.getSelectionModel().getSelectedItem();
        int customerId = selectedCustomer.getCustomerID();
        String customerName = selectedCustomer.getName();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("The selected \"Customer\" and their corresponding \"Appointment(s)\" will be deleted. Do you wish to continue?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            AppointmentDao appointmentDao = new AppointmentDaoConcrete();
            System.out.println(appointmentDao.deleteAllAppointments(customerId));
            System.out.println(customerDao.deleteCustomer(customerId, customerName));
            JDBC.openConnection();
            custTV.setItems(customerDao.getAllCustomers());
        }
    }

    /**
     * This is onActionAppoitnments method
     * <p>This method navigates the user to main.fxml</p>
     * @param actionEvent
     * @throws IOException
     */
    public void onActionAppointments(ActionEvent actionEvent) throws IOException {
        System.out.println("Appointments Button Clicked!");

        stage = (Stage)custmenu.getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void onActionReports(ActionEvent actionEvent) throws IOException {
        System.out.println("Reports Button Clicked!");

        stage = (Stage) custmenu.getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportContact.fxml"));
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
     *This is initialize method
     * <p>This method sets up the initial state of the Main Customers page by populating the time zone label and loading
     * customer dta into the table view</p>
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Customer Records: I am initialized!");

        usertzLB.setText("Your Time Zone: " + String.valueOf(ZoneId.systemDefault()));

        custidCOL.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        custnameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        custaddressCOL.setCellValueFactory(new PropertyValueFactory<>("address"));
        custpostalCOL.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        custcellCOL.setCellValueFactory(new PropertyValueFactory<>("cell"));
        custstateprovinceCOL.setCellValueFactory(new PropertyValueFactory<>("division"));

        JDBC.openConnection();
        CustomerDao customerDao = new CustomerDaoConcrete();
        custTV.setItems(customerDao.getAllCustomers());
    }



}
