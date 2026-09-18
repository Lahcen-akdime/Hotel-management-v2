package main.java.db;

import main.java.Config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private Connection connection;
    private static DatabaseConfig databaseConfig = new DatabaseConfig() ;
    private DatabaseConnection() {
        try{
        Connection connection = DriverManager.getConnection(databaseConfig.getDbUrl(),databaseConfig.getDbUser(), databaseConfig.getDbPassword());
        this.connection = connection ;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}