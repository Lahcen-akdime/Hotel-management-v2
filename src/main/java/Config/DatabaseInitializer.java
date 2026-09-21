package Config;


import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public DatabaseConfig databaseConfig = new DatabaseConfig() ;

    public void executeSqlFile()throws IOException , SQLException {
        try {
            Flyway flyway = Flyway.configure().dataSource(databaseConfig.getDbUrl(),databaseConfig.getDbUser(),databaseConfig.getDbPassword()).load() ;
            flyway.migrate() ;
            //stmt.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
