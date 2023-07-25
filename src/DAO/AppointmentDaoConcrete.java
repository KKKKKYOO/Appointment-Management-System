package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;

import static DAO.JDBC.connection;

/**
 * This is Appointment DAO Conrete class
 * <p> This class implements Appointment DAO interface</p>
 * @author Alfred Yoo
 */
public class AppointmentDaoConcrete implements AppointmentDao{

    /**
     * This is allAppointments list
     * <p>This is a colleciton of all appointments in the database</p>
     */
    ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    /**
     * This is apptFound boolean
     * <p>The boolean value is used in look up methods</p>
     */
    public boolean apptFound;

    /**
     * This is getAllAppointments method
     * <p>This method returns all appointments from the database</p>
     * @return allAppointments
     */
    @Override
    public ObservableList<Appointment> getAllAppointments() {
        try{
            String sql = "SELECT * FROM appointments";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                LocalDateTime startDateTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateTime = result.getTimestamp("End").toLocalDateTime();
                LocalDate startDate = startDateTime.toLocalDate();
                LocalDate endDate = endDateTime.toLocalDate();
                LocalTime startTime = startDateTime.toLocalTime();
                LocalTime endTime = endDateTime.toLocalTime();
                Appointment appointment = new Appointment(appointmentId, customerId, userId, contactId, title, description,
                        location, type, startDateTime, endDateTime, startDate, endDate, startTime, endTime);
                allAppointments.add(appointment);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return allAppointments;
    }

    /**
     * This is getAppointment method
     * <p>This method returns an appointment instance that has corresponding provided appointment ID.
     * The method returns null if no entry if found</p>
     * @param appointmentId
     * @return appt
     */
    @Override
    public Appointment getAppointment(int appointmentId) {
        try{
            String sql = "SELECT * FROM appointments WHERE Appointment_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);

            ResultSet result = ps.executeQuery();
            Appointment appt = null;

            if(result.next()){
                appointmentId = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                LocalDateTime startDateTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateTime = result.getTimestamp("End").toLocalDateTime();
                LocalDate startDate = startDateTime.toLocalDate();
                LocalDate endDate = endDateTime.toLocalDate();
                LocalTime startTime = startDateTime.toLocalTime();
                LocalTime endTime = endDateTime.toLocalTime();
                appt = new Appointment(appointmentId, customerId, userId, contactId, title, description,
                        location, type, startDateTime, endDateTime, startDate, endDate, startTime, endTime);
            }
            return appt;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This is getApptByCustomer method
     * <p>This method returns all appointments under provided customer ID</p>
     * @param customerId
     * @return customerAppts
     */
    @Override
    public ObservableList<Appointment> getApptByCustomer(int customerId) {
        ObservableList<Appointment> customerAppts = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                LocalDateTime startDateTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateTime = result.getTimestamp("End").toLocalDateTime();
                LocalDate startDate = startDateTime.toLocalDate();
                LocalDate endDate = endDateTime.toLocalDate();
                LocalTime startTime = startDateTime.toLocalTime();
                LocalTime endTime = endDateTime.toLocalTime();
                Appointment appointment = new Appointment(appointmentId, customerId, userId, contactId, title, description,
                        location, type, startDateTime, endDateTime, startDate, endDate, startTime, endTime);
                customerAppts.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return customerAppts;
    }

    /**
     * This is getApptByContact method
     * <p>This method returns all appointments under provided contact ID</p>
     * @param contactId
     * @return apptsByContact
     */
    @Override
    public ObservableList<Appointment> getApptByContact(int contactId) {
        ObservableList<Appointment> apptsByContact = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE Contact_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contactId);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");
                int userId = result.getInt("User_ID");
                contactId = result.getInt("Contact_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                LocalDateTime startDateTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateTime = result.getTimestamp("End").toLocalDateTime();
                LocalDate startDate = startDateTime.toLocalDate();
                LocalDate endDate = endDateTime.toLocalDate();
                LocalTime startTime = startDateTime.toLocalTime();
                LocalTime endTime = endDateTime.toLocalTime();
                Appointment appointment = new Appointment(appointmentId, customerId, userId, contactId, title, description,
                        location, type, startDateTime, endDateTime, startDate, endDate, startTime, endTime);
                apptsByContact.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return apptsByContact;
    }

    /**
     * This is getApptByUser method
     * <p>This method returns all appointments under provided user ID</p>
     * @param userId
     * @return apptsByUsers
     */
    @Override
    public ObservableList<Appointment> getApptByUser(int userId) {
        ObservableList<Appointment> apptsByUsers = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM appointments WHERE User_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int appointmentId = result.getInt("Appointment_ID");
                int customerId = result.getInt("Customer_ID");
                userId = result.getInt("User_ID");
                int contactId = result.getInt("Contact_ID");
                String title = result.getString("Title");
                String description = result.getString("Description");
                String location = result.getString("Location");
                String type = result.getString("Type");
                LocalDateTime startDateTime = result.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endDateTime = result.getTimestamp("End").toLocalDateTime();
                LocalDate startDate = startDateTime.toLocalDate();
                LocalDate endDate = endDateTime.toLocalDate();
                LocalTime startTime = startDateTime.toLocalTime();
                LocalTime endTime = endDateTime.toLocalTime();
                Appointment appointment = new Appointment(appointmentId, customerId, userId, contactId, title, description,
                        location, type, startDateTime, endDateTime, startDate, endDate, startTime, endTime);
                apptsByUsers.add(appointment);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return apptsByUsers;
    }

    /**
     * This is updateAppointment method
     * <p>This method updates an existing appointment with provided attributes</p>
     * @param appointmentId
     * @param customerId
     * @param userId
     * @param contactId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @return row number of updated entry
     */
    @Override
    public int updateAppointment(int appointmentId, int customerId, int userId, int contactId, String title, String description,
                                 String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int rowsAffected = 0;
        try {
            String sql = "UPDATE appointments SET Customer_ID=?, User_ID=?, Contact_ID=?, Title=?, Description=?, " +
                    "Location=?, Type=?, Start=?, End=? WHERE Appointment_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, userId);
            ps.setInt(3, contactId);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, location);
            ps.setString(7, type);
            ps.setTimestamp(8, Timestamp.valueOf(startDateTime));
            ps.setTimestamp(9, Timestamp.valueOf(endDateTime));
            ps.setInt(10, appointmentId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment UPDATE was successful!");
            } else {
                System.out.println("Appointment UPDATE Failed!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return rowsAffected;
    }

    /**
     * This is deleteAppointment method
     * <p>This method deletes selected appointment entry</p>
     * @param appointmentId
     * @param customerId
     * @param type
     * @return row number of deleted entry
     */
    @Override
    public int deleteAppointment(int appointmentId, int customerId, String type) {
        int rowsAffected = 0;
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID=? AND Customer_ID=? AND Type=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ps.setInt(2, customerId);
            ps.setString(3, type);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment [" + appointmentId + "] " + type + " was successfully deleted!");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment DELETE");
                alert.setContentText("Appointment [" + appointmentId + "] " + type + " was successfully deleted!");
                alert.showAndWait();
            } else {
                System.out.println("Appointment DELETE failed!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment DELETE");
                alert.setContentText("Appointment [" + appointmentId + "] " + type + " failed to deleted!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return rowsAffected;
    }

    /**
     * This is deleteAllAppointments method
     * <p>This method deletes all appointment entries associated with the given customer ID</p>
     * @param customerId
     * @return number of deleted entries
     */
    @Override
    public int deleteAllAppointments(int customerId) {
        int rowsAffected = 0;
        try {
            String sql = "DELETE FROM appointments WHERE Customer_ID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerId);
            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("All appointments for customer [" + customerId + "] were successfully deleted!");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointments DELETE");
                alert.setContentText("All appointments for customer [" + customerId + "] were successfully deleted!");
                alert.showAndWait();
            } else {
                System.out.println("Appointments DELETE failed!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointments DELETE");
                alert.setContentText("Failed to delete appointments for customer [" + customerId + "]!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return rowsAffected;
    }



    /**
     * This is addAppointment method
     * <p>This method inserts a new appointment entry to the database with provided attributes</p>
     * @param customerId
     * @param userId
     * @param contactId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @return row number of inserted entry
     */
    @Override
    public int addAppointment(int customerId, int userId, int contactId, String title, String description, String location,
                              String type, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int rowsAffected = 0;

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO appointments (Customer_ID, User_ID, Contact_ID, Title, Description, Location, Type, Start, End) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            ps.setInt(1, customerId);
            ps.setInt(2, userId);
            ps.setInt(3, contactId);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, location);
            ps.setString(7, type);
            ps.setTimestamp(8, Timestamp.valueOf(startDateTime));
            ps.setTimestamp(9, Timestamp.valueOf(endDateTime));

            rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Appointment INSERT was successful!");
            } else {
                System.out.println("Appointment INSERT failed!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        return rowsAffected;
    }


    /**
     * This is lookupAppointment method
     * <p>This method returns all appointments under provided start date
     * if no appointments are found it returns all appointments</p>
     * @param selDate
     * @return filteredAppts
     */
    @Override
    public ObservableList<Appointment> lookUpAppointment(LocalDate selDate) {
        ObservableList<Appointment> filteredAppts = FXCollections.observableArrayList();
        apptFound = false;

        for (Appointment appointment : allAppointments) {
            if (appointment.getStartDate().equals(selDate)) {
                filteredAppts.add(appointment);
            }
        }
        if (filteredAppts.isEmpty()) {
            return allAppointments;
        }
        apptFound = true;
        return filteredAppts;
    }

    /**
     * This is upcomingApptAlert method
     * <p>This method displays alert message regarding upcoming appointment in next 15 minutes</p>
     * @param ldt
     */
    @Override
    public void upcomingApptAlert(LocalDateTime ldt) {
        try {
            AppointmentDao appointmentDao = new AppointmentDaoConcrete();
            ObservableList<Appointment> allAppts = appointmentDao.getAllAppointments();

            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime loginZDT = ldt.atZone(zoneId);
            LocalDateTime apptStart = ldt.plusMinutes(15);

            ObservableList<Appointment> upcomingAppts = allAppts.filtered(appt ->
                    appt.getStartDateTime().isAfter(loginZDT.toLocalDateTime()) && appt.getStartDateTime().isBefore(apptStart)
            );

            if (upcomingAppts.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Upcoming Appointments");
                alert.setContentText("There are no appointments scheduled to begin in the next 15 minutes!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upcoming Appointments");
                alert.setHeaderText("The following appointments are scheduled to begin in the next 15 minutes:");
                StringBuilder alertText = new StringBuilder();

                for (Appointment upAppt : upcomingAppts) {
                    String apptInfo = String.format("Appointment: [%d] at %s (%s)%n",
                            upAppt.getApptId(), upAppt.getStartTime(), upAppt.getStartDate());
                    alertText.insert(0, apptInfo);
                }

                alert.setContentText(alertText.toString());
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * This is apptsWeekly method
     * <p>This method returns all appointments in the week</p>
     * @param dateAtLogin
     * @return
     */
    @Override
    public FilteredList<Appointment> apptsWeekly(LocalDate dateAtLogin) {
        ObservableList<Appointment> allAppts = getAllAppointments();
        FilteredList<Appointment> filteredAppts = new FilteredList<>(allAppts);

        filteredAppts.setPredicate(appointment -> {
            LocalDate apptDate = appointment.getStartDate();

            return (apptDate.isEqual(dateAtLogin) ||
                    (apptDate.isAfter(dateAtLogin) && apptDate.isBefore(dateAtLogin.plusDays(7))));
        });

        return filteredAppts;
    }

    /**
     * This is apptsMonthly method
     * <p>This method returns all appointments in the month</p>
     * @param dateAtLogin
     * @return
     */
    @Override
    public FilteredList<Appointment> apptsMonthly(LocalDate dateAtLogin) {
        ObservableList<Appointment> allAppts = getAllAppointments();
        FilteredList<Appointment> filteredAppts = new FilteredList<>(allAppts);

        filteredAppts.setPredicate(appointment -> {
            LocalDate apptDate = appointment.getStartDate();

            return apptDate.isEqual(dateAtLogin) ||
                    (apptDate.isAfter(dateAtLogin) && apptDate.getMonth() == dateAtLogin.getMonth());
        });

        return filteredAppts;
    }

    /**
     * This is checkApptsStartTime method
     * <p>This method returns boolean value to whether the start date is within business hours</p>
     * @param apptStartTime
     * @return
     */
    @Override
    public boolean checkApptStartTime(LocalDateTime apptStartTime) {
        ZoneId systemZone = ZoneId.systemDefault();
        ZoneId easternZone = ZoneId.of("US/Eastern");

        ZonedDateTime apptZone = apptStartTime.atZone(systemZone).withZoneSameInstant(easternZone);
        LocalTime apptTime = apptZone.toLocalTime();

        LocalTime businessOpen = LocalTime.of(8, 0);
        LocalTime businessClose = LocalTime.of(22, 0);

        return apptTime.isAfter(businessOpen) && apptTime.isBefore(businessClose);
    }

    /**
     * This is checkApptsEndTime method
     * <p>This method returns boolean value to whether the end time is within business hours</p>
     * @param apptEndTime
     * @return
     */
    @Override
    public boolean checkApptEndTime(LocalDateTime apptEndTime) {
        ZoneId systemZone = ZoneId.systemDefault();
        ZoneId easternZone = ZoneId.of("US/Eastern");

        ZonedDateTime apptZone = apptEndTime.atZone(systemZone).withZoneSameInstant(easternZone);
        LocalTime apptTime = apptZone.toLocalTime();

        LocalTime businessOpen = LocalTime.of(8, 0);
        LocalTime businessClose = LocalTime.of(22, 0);

        return apptTime.isAfter(businessOpen) && (apptTime.isBefore(businessClose) || apptTime.equals(businessClose));
    }

    /**
     * This is checkNewApptsForOverlap method
     * <p>This method filters a list of all appointments retreived by calling the getApptByCustomer().
     * It checks for overlapping appointments based on several requirements: </p>
     * <p>• Whether the appointments start or end on the same day</p>
     * <p>• Whether the appointments start at the same time</p>
     * <p> Whether any of the existing customer appointments occur within the selected start and end times of the new appointment</p>
     * <p> Whether the new appointment starts before an existing appointment and ends after it starts</p>
     * <p> If any of theses checks evaluate to true, it indicates an overlap with the selected appointment date and times</p>
     * @param customerId
     * @param selStartDate
     * @param selEndDate
     * @param selStartTime
     * @param selEndTime
     * @return boolean
     */
    @Override
    public boolean checkNewApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime, LocalTime selEndTime) {
        AppointmentDao apptDao = new AppointmentDaoConcrete();
        ObservableList<Appointment> customerAppts = apptDao.getApptByCustomer(customerId);

        for (Appointment appt : customerAppts) {
            if (appt.getStartDate().isEqual(selStartDate) || appt.getEndDate().isEqual(selEndDate) ||
                    (appt.getStartDate().isBefore(selEndDate) && appt.getEndDate().isAfter(selStartDate))) {
                if (appt.getStartTime().equals(selStartTime) || // Start at the same time
                        (appt.getStartTime().isBefore(selEndTime) && appt.getEndTime().isAfter(selStartTime)) ||
                        (selStartTime.isBefore(appt.getEndTime()) && selEndTime.isAfter(appt.getStartTime()))) {
                    return true; // Overlapping appointment found
                }
            }
        }

        return false; // No overlapping appointments found
    }

    /**
     /**
     * This is check updated appointment for overlap method
     * <p>This method filters a list of all appointments retreived by calling the getApptByCustomer().
     * It checks for overlapping appointments based on several requirements: </p>
     * <p>• Whether the appointments start or end on the same day</p>
     * <p>• Whether the appointments start at the same time</p>
     * <p> Whether any of the existing customer appointments occur within the selected start and end times of the new appointment</p>
     * <p> Whether the new appointment starts before an existing appointment and ends after it starts</p>
     * <p> If any of theses checks evaluate to true, it indicates an overlap with the selected appointment date and times</p>
     * @param customerId
     * @param selStartDate
     * @param selEndDate
     * @param selStartTime
     * @param selEndTime
     * @param apptId
     * @return boolean
     */
    @Override
    public boolean checkUpdatedApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate,
                                              LocalTime selStartTime, LocalTime selEndTime, int apptId) {
        AppointmentDao apptDao = new AppointmentDaoConcrete();
        ObservableList<Appointment> customerAppts = apptDao.getApptByCustomer(customerId);

        for (Appointment appt : customerAppts) {
            if (appt.getApptId() != apptId) { // Skip the appointment being updated
                if (appt.getStartDate().isEqual(selStartDate) || appt.getEndDate().isEqual(selEndDate) ||
                        (appt.getStartDate().isBefore(selEndDate) && appt.getEndDate().isAfter(selStartDate))) {
                    if (appt.getStartTime().equals(selStartTime) ||
                            (appt.getStartTime().isBefore(selEndTime) && appt.getEndTime().isAfter(selStartTime)) ||
                            (selStartTime.isBefore(appt.getEndTime()) && selEndTime.isAfter(appt.getStartTime()))) {
                        return true; // Overlapping appointment found
                    }
                }
            }
        }

        return false;
    }

}
