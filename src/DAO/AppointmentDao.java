package DAO;

import javafx.collections.ObservableList;

import model.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This is the Appointmnet DAO class
 * @author Alfred Yoo
 */
public interface AppointmentDao {
    /**
     * This method returns all appointments from the database
     * @return allAppointments
     */
    public ObservableList<Appointment> getAllAppointments();

    /**
     * This method returns an appointment instance that has corresponding provided appointment ID
     * @param appointmentId
     * @return appointment
     */
    public Appointment getAppointment(int appointmentId);

    /**
     * This method returns all appointments under provided customer ID
     * @param customerId
     * @return customerAppts
     */
    public ObservableList<Appointment> getApptByCustomer(int customerId);

    /**
     * This method returns all appointments under provided contact ID
     * @param contactId
     * @return contactAppts
     */
    public ObservableList<Appointment> getApptByContact(int contactId);

    /**
     * This method returns all appointmnets under provided user ID
     * @param userId
     * @return userAppts
     */
    public ObservableList<Appointment> getApptByUser(int userId);

    /**
     * This is update appointment method
     * <p>This method updates all attributes of a selected appointment</p>
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
     * @return row number of the updated entry
     */
    public int updateAppointment(int appointmentId, int customerId, int userId, int contactId, String title, String description,
                                 String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime);

    /**
     * This is delete appointment method
     * <p>This method deletes an existing appointment under provided appointment</p>
     * @param appointmentId
     * @param customerId
     * @param type
     * @return row number of deleted entry
     */
    public int deleteAppointment(int appointmentId, int customerId, String type);

    /**
     * This is add appointment method
     * <p>This method inserts a new appointment with provided attribute values</p>
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
    public int addAppointment(int customerId, int userId, int contactId, String title, String description,
                              String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime);

    /**
     * This is delete all appointment method
     * <p>This methods delete all appointments associated with provided customer ID</p>
     * @param customerId
     * @return
     */
    public int deleteAllAppointments(int customerId);

    /**
     * This is look up appointment method
     * <p>This method returns all appointments under provided start date.
     * If no appointments are found, it returns all appointments</p>
     * @param date
     * @return filteredAppts
     */
    public ObservableList<Appointment> lookUpAppointment(LocalDate date);

    /**
     * This is upcoming appointment alert method
     * <p>This method displays alert message regarding upcoming appointment in next 15 minutes </p>
     * @param loginLDT
     */
    public void upcomingApptAlert(LocalDateTime loginLDT);

    /**
     * This is weekly appointments method
     * @param loginLD
     * @return filteredAppts
     */
    public ObservableList<Appointment> apptsWeekly(LocalDate loginLD);

    /**
     * This is monthly appointments method
     * @param loginLD
     * @return filteredAppts
     */
    public ObservableList<Appointment> apptsMonthly(LocalDate loginLD);

    /**
     * This is check appointment start time method
     * <p>This method returns boolean value to whether the start time is within business hours</p>
     * @param apptStartTime
     * @return boolean
     */
    public boolean checkApptStartTime(LocalDateTime apptStartTime);

    /**
     * This is check appointment end time method
     * <p>This method returns boolean value to whether the end time is within business hours</p>
     * @param apptEndTime
     * @return boolean
     */
    public boolean checkApptEndTime(LocalDateTime apptEndTime);

    /**
     * This is check new appointment for overlap method
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
    public boolean checkNewApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime,
                                          LocalTime selEndTime);

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
    public boolean checkUpdatedApptForOverlap(int customerId, LocalDate selStartDate, LocalDate selEndDate, LocalTime selStartTime,
                                              LocalTime selEndTime, int apptId);
}
