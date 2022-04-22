package crud;

import config.Config;
import models.AuthorModel;
import utils.FileUtils;

import java.sql.*;

public class AuthorTable {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static AuthorModel select(String name) {
        AuthorModel model = new AuthorModel();
        try {
            con = DriverManager.getConnection(Config.environment.getValue("/url").toString(), Config.environment.getValue("/user").toString(), Config.environment.getValue("/password").toString());
            stmt = con.createStatement();
            rs = stmt.executeQuery(String.format(FileUtils.readFile("src/main/resources/getAuthor.sql"), name));
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        try {
            if(rs == null) {
                model.setName("");
                model.setId(0);
            }else {
                while(rs.next()){
                    model.setName(rs.getString(1));
                    model.setId(rs.getInt(2));
            }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return model;
    }

    public static void add() {
        PreparedStatement prSt = null;
        try {
            con = DriverManager.getConnection(Config.environment.getValue("/url").toString(), Config.environment.getValue("/user").toString(), Config.environment.getValue("/password").toString());
            prSt = con.prepareStatement(FileUtils.readFile("src/main/resources/AddAuthor.sql"));
            prSt.setString(1, Config.environment.getValue("/authorName").toString());
            prSt.setString(2, Config.environment.getValue("/authorLogin").toString());
            prSt.setString(3, Config.environment.getValue("/authorEmail").toString());
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
