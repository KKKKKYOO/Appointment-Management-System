package controller;

import DAO.*;
import javafx.fxml.FXML;
import utilities.ListManager;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.Division;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is AddCustomer method
 * <p>This method is a controller for AddCustomer.fxml</p>
 */
public class AddCustomer implements Initializable{
    private Stage stage;
    private Parent scene;

    /**
     * This is Customer Address text field
     */
    @FXML
    private TextField addcustaddyTF;

    /**
     * This is Customer Cell Number text field
     */
    @FXML
    private TextField addcustcellTF;

    /**
     * This is Customer Country combo box
     */
    @FXML
    private ComboBox<Country> addcustcountryCB;

    /**
     * This is Customer Division combo box
     */
    @FXML
    private ComboBox<String> addcustdivisionCB;

    /**
     * This is Customer ID text field
     */
    @FXML
    private TextField addcustidTF;

    /**
     * This is Customer Name text field
     */
    @FXML
    private TextField addcustnameTF;

    /**
     * This is Cancel button
     */
    @FXML
    private Button addcustomercancelBT;

    /**
     * This is Save button
     */
    @FXML
    private Button addcustomersaveBT;

    /**
     * This is Customer Postal Code text field
     */
    @FXML
    private TextField addcustpostTF;

    /**
     * This is Country ID
     */
    private int countryId;

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
     * This is Customer Phone
     */
    public String phone;

    /**
     * This is saveState boolean
     */
    boolean saveState = false;

    /**
     * This is onActionSave method
     * This method saves a new Customer entry to the database
     * @param actionEvent
     */

    public void onActionSave(ActionEvent actionEvent) {
        System.out.println("Save Button clicked!");

        try {
            custerName = addcustnameTF.getText();
            address = addcustaddyTF.getText();
            postalCode = addcustpostTF.getText();
            phone = addcustcellTF.getText();
            ObservableList<Division> d=ListManager.getFilteredDivisions(countryId);
            int divisionId=0;
            for(Division D:d)
            {
                if(D.getDivision().equals(addcustdivisionCB.getValue()))
                {
                    divisionId=D.getDivisionId();
                }
            }

            while(saveState == false){
                requiredFieldCheck(custerName, address, postalCode, phone);
            }
            CustomerDao customerDao = new CustomerDaoConcrete();
            customerDao.addCustomer(custerName, address, postalCode, phone, divisionId);

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/customerMain.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            emptyFields.add("Name");
        }
        if (address.isBlank()) {
            emptyFields.add("Address");
        }
        if (postalCode.isBlank()) {
            emptyFields.add("postalCode");
        }
        if (phone.isBlank()) {
            emptyFields.add("Cell");
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
     * This is onActionSelectCountry method
     * <p>This method populates country combo box with countries</p>
     * <p>Lambda Expression: `division -> s.add(division.getDibision()))` is used to iterate over the list of division objects.
     * It adds each divisions' name to the `s` list.</p>
     * @param actionEvent
     */
    public void onActionSelectCountry(ActionEvent actionEvent) {
        countryId = addcustcountryCB.getValue().getCountryID();
        ObservableList<Division> d = ListManager.getFilteredDivisions(countryId);
        ArrayList<String> s = new ArrayList<>();

        d.forEach(division -> s.add(division.getDivision()));

        addcustdivisionCB.setItems(FXCollections.observableArrayList(s));
        addcustdivisionCB.getSelectionModel().selectFirst();
    }


    /**
     * This is onActionCancel method
     * @param actionEvent
     * @throws IOException
     */
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        System.out.println("Cancel Button clicked!");

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel \"Add Customer\"");
        alert.setContentText("All changes will be forgotten, do you wish to continue?");
        alert.showAndWait();

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/customerMain.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
            JDBC.closeConnection();
        }
    }

    /**
     * This is initialize method
     * <p>This method initializes the customer addition screen by establishing a database connection, retrieving and populating
     * the country and division combo boxes </p>
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add Customer: I am initialized!");
        try {
            JDBC.openConnection();
            CountryDao countryDao = new CountryDaoConcrete();

            addcustcountryCB.setItems(countryDao.getAllCountries());
            addcustcountryCB.getSelectionModel().selectFirst();
            countryId = addcustcountryCB.getValue().getCountryID();
            ObservableList<Division> d=ListManager.getFilteredDivisions(countryId);
            ArrayList<String> s=new ArrayList<>();
            for(Division d1:d){
                s.add(d1.getDivision());
            }

            addcustdivisionCB.setItems(FXCollections.observableArrayList(s));
            addcustdivisionCB.getSelectionModel().selectFirst();
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }



}
