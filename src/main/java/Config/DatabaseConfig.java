package main.java.Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    Properties properties = new Properties() ;

    String filePath = "src/main/resources/db.properties" ;
    String dbUrl ;
    String dbUser ;
    String dbPassword  ;

    public DatabaseConfig(){
        try{
            InputStream input = new FileInputStream(filePath) ;
            properties.load(input);
            dbUrl = properties.getProperty("POSTGRES_DB_Url") ;
            dbUser = properties.getProperty("POSTGRES_USER") ;
            dbPassword = properties.getProperty("POSTGRES_PASSWORD") ;
        } catch(IOException e){
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
