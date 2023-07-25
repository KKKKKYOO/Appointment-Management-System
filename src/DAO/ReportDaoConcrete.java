package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Report;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.JDBC.connection;

public class ReportDaoConcrete implements ReportDao{

    /**
     * list of all reports
     */
    ObservableList<Report> allReports = FXCollections.observableArrayList();

    /**
     * This method retrieves all entries (Reports) from the database and appends them to "allReports".
     * @return allReports
     */
    @Override
    public ObservableList<Report> getAllReports(){
        try{
            String sql = "SELECT monthname(start), type, count(*) as cnt FROM appointments GROUP BY monthname(start), type";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while(result.next()){
                String month = result.getString("monthname(start)");
                String type = result.getString("type");
                int count = result.getInt("cnt");
                Report report = new Report(month, type, count);
                allReports.add(report);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return allReports;
    }
}
