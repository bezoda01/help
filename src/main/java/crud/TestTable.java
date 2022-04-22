package crud;

import config.Config;
import models.TestModel;
import utils.FileUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestTable {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

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

    public static List<TestModel> select(String inquiry) {
        List<TestModel> tests = new ArrayList<>();
        try {
            con = DriverManager.getConnection(Config.environment.getValue("/url").toString(), Config.environment.getValue("/user").toString(), Config.environment.getValue("/password").toString());
            stmt = con.createStatement();
            rs = stmt.executeQuery(inquiry);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        try {
            while (rs.next()) {
                tests.add(new TestModel(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getInt(11)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if(con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tests;
    }

    public static List<TestModel> update(String date, int limit) {
        List<TestModel> tests = new ArrayList<>();
        try {
            con = DriverManager.getConnection(Config.environment.getValue("/url").toString(), Config.environment.getValue("/user").toString(), Config.environment.getValue("/password").toString());
            stmt = con.createStatement();
            stmt.executeUpdate(String.format(FileUtils.readFile("src/main/resources/updateTest.sql"), date, limit));
            rs = stmt.executeQuery(FileUtils.readFile("src/main/resources/selectTest.sql")+"order by id desc limit "+limit);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        try {
            while (rs.next()) {
                tests.add(new TestModel(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getInt(11)));
            }}catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if(con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tests;
    }

    public static void delete(int limit) {
        try {
            con = DriverManager.getConnection(Config.environment.getValue("/url").toString(), Config.environment.getValue("/user").toString(), Config.environment.getValue("/password").toString());
            stmt = con.createStatement();
            stmt.executeUpdate(String.format(FileUtils.readFile("src/main/resources/deleteTest.sql"), limit));
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
}
