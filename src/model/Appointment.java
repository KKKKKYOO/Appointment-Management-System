package model;

import java.time.*;
import java.sql.Timestamp;


/**
 * This is Appointment class.
 * @author Alfred Yoo
 */
public class Appointment {
    /**
     * Appointment ID
     */
    private int apptId;
    /**
     * Customer ID;
     */
    private int customerId;
    /**
     * User ID;
     */
    private int userId;
    /**
     * Contact ID;
     */
    private int contactId;
    /**
     * Appointmnent Title
     */
    private String title;
    /**
     * Appointment Description
     */
    private String desc;
    /**
     * Appointment Location
     */
    private String location;
    /**
     * Appointmnent Type
     */
    private String type;
    /**
     * Appointment Start Date Time
     */
    private LocalDateTime startDateTime;

    /**
     * Appointment End Date Time
     */
    private LocalDateTime endDateTime;
    /**
     * Appointment Start Date
     */
    private LocalDate startDate;
    /**
     * Appointment End Date
     */
    private LocalDate endDate;

    /**
     * Appointment Start Time
     */
    private LocalTime startTime;

    /**
     * Appointmnet End Time
     */
    private LocalTime endTime;

    /**
     * Appointment class Constructor
     * @param apptId
     * @param customerId
     * @param userId
     * @param contactId
     * @param title
     * @param desc
     * @param location
     * @param type
     * @param startDateTime
     * @param endDateTime
     * @param startDate
     * @param endDate
     * @param startTime
     * @param endTime
     */
    public Appointment(int apptId, int customerId, int userId, int contactId, String title, String desc,
                       String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime,
                       LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        this.apptId = apptId;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.title = title;
        this.desc = desc;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * This method returns appointment id
     * @return apptId
     */
    public int getApptId() {
        return apptId;
    }

    /**
     * This method sets appointmnet id
     * @param apptId
     */
    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    /**
     * This method returns customer id
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * This method sets customer id
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * This method returns user id
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * This method sets user id
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * This method returns contact id
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * This method sets contact id
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * This method returns appointment title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method sets appointment title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method returns appointment description
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method sets appointment description
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * This method returns appointmnet location
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method sets appoitment location
     * @param location
     */

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method returns appointment type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * This method sets appointment type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method returns appointment start date time
     * @return startDateTime
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * This method sets appointment start date time
     * @param startDateTime
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * This method returns appointment end date time
     * @return
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * This method sets appointment end date time
     * @param endDateTime
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * This method returns appointment start date
     * @return startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * This method sets appointment start date
     * @param startDate
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * This method returns appointment end date
     * @return
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Thie method sets appointment end date
     * @param endDate
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * This method returns appointment start time
     * @return
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * This method sets appointment start time
     * @param startTime
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * This method returns appointment end time
     * @return endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * This method sets appointment end time
     * @param endTime
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString(){
        return ("ApptID: " + Integer.toString(getApptId()) + "\nCustomerID: " + Integer.toString(getCustomerId()) +
                "\nContactID: " + Integer.toString(getContactId()) + "\nType: " + getType() +
                "\n Start: " + getStartTime() + "\nEnd: " + getEndTime());
    }
}
