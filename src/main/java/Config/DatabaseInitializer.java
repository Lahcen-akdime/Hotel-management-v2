package main.java.Config;

import main.java.db.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public String filePath = "src/main/resources/schema.sql" ;

    public void executeSqlFile(Connection connection )throws IOException , SQLException {
        try {
        Statement stmt = connection.createStatement() ;
        //stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
