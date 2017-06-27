/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClient.Db;

import RestClient.Entity.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 *
 * @author wilzone
 */
public class DbHelper {

    public static Connection conn;

    public static void connectToDb() {
        Properties properties = new Properties();
        try {
            properties.load(DbHelper.class.getClassLoader().getResourceAsStream("dbConfig.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String url = properties.getProperty("dbConfig.url");
        String dbName = properties.getProperty("dbConfig.dbName");
        String driver = properties.getProperty("dbConfig.driver");
        String userName = properties.getProperty("dbConfig.userName");
        String password = properties.getProperty("dbConfig.password");

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet signInDataEntry(User user) throws Exception {
        try {
            String query = "Insert into users (firstname,lastname,signintime,phonenumber) values (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setDate(3, new Date(user.getSignInTime().getTime()));
            stmt.setString(4, user.getPhoneNumber());
            stmt.executeUpdate();
            return stmt.getGeneratedKeys();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    //run query with data update e.g. update, etc
    public static int executeUpdate(String query) throws Exception {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    //run query with no manipulation e.g. select, etc
    public static ResultSet executeQuery(String query) throws Exception {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public static void closeDbConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
