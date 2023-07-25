package controller;
import DAO.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Customer;
import model.Division;
import utilities.ListManager;

/**
 * This is Update Customer class
 * <p>This class is a controller for UpdateCustomer.fxml</p>
 * @author Alfred Yoo
 */
public class UpdateCustomer implements Initializable {
    private Stage stage;
    private Parent scene;


    /**
     * This is Customer Address text field
     */
    @FXML
    private TextField upcustaddyTF;

    /**
     * This is Customer Cell Number text field
     */
    @FXML
    private TextField upcustcellTF;

    /**
     * This is Country combo box
     */
    @FXML
    private ComboBox<Country> upcustcountryCB;

    /**
     * This is Division combo box
     */
    @FXML
    private ComboBox<Division> upcustdivisionCB;

    /**
     * THis is Customer ID text field
     */
    @FXML
    private TextField upcustidTF;

    /**
     * This is Customer Name text field
     */
    @FXML
    private TextField upcustnameTF;

    /**
     * This is Cancel Update button
     */
    @FXML
    private Button upcustomercancelBT;

    /**
     * This is Save Update button
     */
    @FXML
    private Button upcustomersaveBT;

    /**
     * This is Customer Postal Code text field
     */
    @FXML
    private TextField upcustpostTF;

    /**
     * This is Country ID
     */
    public int countryId;

    /**
     * This is Division ID
     */
    public int divisionId;

    /**
     * This is Customer Name
     */
    public String custerName;

    /**
     * This is Customer Address
     */
    public String address;

    /**
     * This is Customer Postal Code
     */
    public String postalCode;

    /**
     * This is Customer Cell Number
     */
    public String phone;

    /**
     * This is Update Save State boolean
     */
    boolean saveState = false;

    /**
     * This is Selected Customer instance
     */

    Customer selCust = null;

    /**
     * This is updateCustomer method
     * <p>This method poopulates text fileds and combo boxes with the selected customer attributes</p>
     * <p>Lambda Expression: The lambda expression replaces the for loop. The filter() is used to filter the divisions based on the condition.
     * The findFirst() is used to return the first division that matches the condition.</p>
     * @param selectedCustomer
     */
    public void updateCustomer(Customer selectedCustomer) {
        JDBC.openConnection();
        CountryDao countryDao = new CountryDaoConcrete();

        selCust = selectedCustomer;
        upcustnameTF.setText(String.valueOf(selCust.getName()));
        upcustaddyTF.setText(String.valueOf(selCust.getAddress()));
        upcustpostTF.setText(String.valueOf(selCust.getPostalCode()));
        upcustcellTF.setText(String.valueOf(selCust.getCell()));

        Country selCountry = null;
        for(Country country : countryDao.getAllCountries()) {
            if(country.getCountryID() == selectedCustomer.getCountryId()) {
                selCountry = country;
                break;
            }
        }
        upcustcountryCB.getSelectionModel().select(selCountry);
        countryId = selCountry.getCountryID();

        upcustdivisionCB.setItems(ListManager.getFilteredDivisions(countryId));
        Division selDivision = null;
        for(Division division : ListManager.getFilteredDivisions(countryId)){
            if(division.getDivisionId() == selCust.getDivisionId()) {
                selDivision = division;
                break;
            }
        }

        upcustdivisionCB.getSelectionModel().select(selDivision);
        divisionId = selDivision.getDivisionId();
    }

    /**
     * This is onActionSave method
     * <p>This method handles the validation and updating of a customer. It checks for blank fields, updates the customer in the database,
     * and repopulates the Main Customers table view.</p>
     * @param actionEvent
     */
    public void onActionSave(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");

        try {
            custerName = upcustnameTF.getText();
            address = upcustaddyTF.getText();
            postalCode = upcustpostTF.getText();
            phone = upcustcellTF.getText();
            divisionId = upcustdivisionCB.getValue().getDivisionId();

            while(saveState == false){
                requiredFieldCheck(custerName, address, postalCode, phone);
            }
            CustomerDao customerDao = new CustomerDaoConcrete();

            customerDao.updateCustomer(selCust.getCustomerID(), custerName, address, postalCode, phone, divisionId);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/customerMain.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This is onActionSelectCountry method
     * <p>This method populates country combo box with countries</p>
     * <p>Lambda Expression: `division -> s.add(division.getDibision()))` is used to iterate over the list of division objects.
     * It adds each divisions' name to the `s` list.</p>
     * @param actionEvent
     */
    public void onActionSelectCountry(ActionEvent actionEvent) {
        countryId = upcustcountryCB.getValue().getCountryID();
        upcustdivisionCB.setItems(ListManager.getFilteredDivisions(countryId));
        upcustdivisionCB.getSelectionModel().selectFirst();
    }
    /**
     * This is requiredFieldCheck method
     * <p>This method checks for any blank fields</p>
     * @param custerName
     * @param address
     * @param postalCode
     * @param phone
     */
    public void requiredFieldCheck(String custerName, String address, String postalCode, String phone){

        List<String> emptyFields = new ArrayList<>();

        if (custerName.isBlank()) {
            emptyFields.add("Title");
        }
        if (address.isBlank()) {
            emptyFields.add("Description");
        }
        if (postalCode.isBlank()) {
            emptyFields.add("Location");
        }
        if (phone.isBlank()) {
            emptyFields.add("Type");
        }

        if (!emptyFields.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning: Fields cannot be left blank");
            alert.setHeaderText(null);
            alert.setContentText("The following fields cannot be left blank:\n" + String.join("\n", emptyFields));
            alert.showAndWait();
        } else {
            saveState = true;
        }
    }

    /**
     * This is onactionCancel method
     * @param actionEvent
     * @throws IOException
     */

    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel \"Update Customer\"");
        alert.setContentText("All changes will be forgotten, do you wish to continue?");
        alert.showAndWait();
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/customerMain.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * This is initialize method
     * <p>This method sets up the initial state of the screen by populating the country combo box with values retrieved
     * from the database</p>
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Update Customer: I am initialized!");

        JDBC.openConnection();
        CountryDao countryDao = new CountryDaoConcrete();

        upcustcountryCB.setItems(countryDao.getAllCountries());
    }
}
