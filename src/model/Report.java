package model;

/**
 * This is Report class
 */
public class Report {
    /**
     * appointment month.
     * */
    private String month;
    /**
     * appointment type.
     * */
    private String type;
    /**
     * number of appointments, grouped by month and type.
     * */
    private int count;

    /**
     * This is the Month &amp; Type Report constructor.
     *
     * @param month appointment month
     * @param type appointment type
     * @param count number of appointments, grouped by month and type
     * */
    public Report(String month, String type, int count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    /**
     * This method returns appointment months
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * This method sets months
     * @param month appointment month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * This method returns appointment
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * This method sets appointment type
     * @param type appointment type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method return the total number of appointments
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * This method set the total number of appointment
     * @param count number of appointments to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return ("Report: " + month + " " + type + " " + Integer.toString(count));
    }

}
