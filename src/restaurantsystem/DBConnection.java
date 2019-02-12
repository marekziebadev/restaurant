/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kom1
 */
public class DBConnection {
    static Connection connection=null;
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:restaurant.sqlite";
    public static Connection getConnection()
    {
        try {
            Class.forName (DRIVER);
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException e) {
        }
        return connection;
    }
    public static void main(String[] args)
    {
        getConnection();
    }
}
