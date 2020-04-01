/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import loader.SettingsLoader;

/**
 *
 * @author not-sure
 */
public class DatabaseConnection {
    private final Connection connection;
    private static DatabaseConnection instance;
    
    private DatabaseConnection() throws SQLException{
        String url = SettingsLoader.getInstance().getProperty("url");
        String user = SettingsLoader.getInstance().getProperty("user");
        String pass = SettingsLoader.getInstance().getProperty("pass");
        connection = DriverManager.getConnection(url, user, pass);
        connection.setAutoCommit(false);
    }
    
    public static DatabaseConnection getInstance() throws SQLException{
        return instance = instance == null ? new DatabaseConnection() : instance;
    }
    
    public Connection getConnection(){
        return connection;
    }
}
