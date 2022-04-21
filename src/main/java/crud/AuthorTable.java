package crud;

import config.Config;
import models.AuthorModel;
import java.sql.*;

public class AuthorTable {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static AuthorModel checkIsAuthorExist(String inquiry) {
        AuthorModel model = new AuthorModel();
        try {
            con = DriverManager.getConnection(Config.environment.getValue("/url").toString(), Config.environment.getValue("/user").toString(), Config.environment.getValue("/password").toString());
            stmt = con.createStatement();
            rs = stmt.executeQuery(inquiry);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        try {
            while(rs.next()){
            model.setName(rs.getString(1));
            model.setId(rs.getInt(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return model;
    }

    public static void add(AuthorModel model, String inquiry) {
        PreparedStatement prSt = null;
        try {
            con = DriverManager.getConnection(Config.environment.getValue("/url").toString(), Config.environment.getValue("/user").toString(), Config.environment.getValue("/password").toString());
            prSt = con.prepareStatement(inquiry);
            prSt.setString(1, model.getName());
            prSt.setString(2, model.getLogin());
            prSt.setString(3, model.getEmail());
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
