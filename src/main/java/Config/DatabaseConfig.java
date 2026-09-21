package Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    Properties properties = new Properties() ;

    String filePath = "src/main/resources/db.properties" ;
    static String dbUrl ;
    static String dbUser ;
    static String dbPassword  ;

    public DatabaseConfig(){
        try{
            InputStream input = new FileInputStream(filePath) ;
            properties.load(input);
            dbUrl = properties.getProperty("db.url") ;
            dbUser = properties.getProperty("db.username") ;
            dbPassword = properties.getProperty("db.password") ;
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public String getDbUser(){
        return dbUser ;
    }

    public String getDbUrl(){
        return dbUrl ;
    }

    public String getDbPassword(){
        return dbPassword ;
    }
}
