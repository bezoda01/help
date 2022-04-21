package crud;

import config.Config;
import models.TestModel;
import java.sql.*;

public class TestTable {
    private static Connection con;

    public static void add(TestModel model, String inquiry) {
        PreparedStatement prSt = null;
        try {
            con = DriverManager.getConnection(Config.environment.getValue("/url").toString(), Config.environment.getValue("/user").toString(), Config.environment.getValue("/password").toString());
            prSt = con.prepareStatement(inquiry);
            prSt.setString(1, model.getName());
            prSt.setInt(2, model.getStatus_id());
            prSt.setString(3, model.getMethod_name());
            prSt.setInt(4, model.getProject_id());
            prSt.setInt(5, model.getSession_id());
            prSt.setString(6, model.getStart_time());
            prSt.setString(7, model.getEnd_time());
            prSt.setString(8, model.getEnv());
            prSt.setString(9, model.getBrowser());
            prSt.setInt(10, model.getAuthor_id());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prSt != null) {
                    prSt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
