package DAO;

import javafx.collections.ObservableList;
import model.Report;

/**
 * This is Report DAO class\
 * @author Alfred Yoo
 */
public interface ReportDao {

    /**
     * This method return all reports in the database in list
     * @return allReports list
     */
    public ObservableList<Report> getAllReports();
}
