package application.data.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class DatabaseUtils {
    Properties properties;
    Connection connection = null;
    String propertiesFileName;
    private static final Logger LOGGER = LogManager.getLogger();

    public DatabaseUtils() {
        LOGGER.info("--Database created--");
        //TODO: SI AICI
        this.properties = new Properties();
        this.propertiesFileName = "database.config";
        loadProperties();
    }

    public Connection getConnection(){
        //Daca conexiunea e null sau e inchisa, atunci getNewConnection
        //asta e singleton cum ar veni

        try {
            if(connection == null || connection.isClosed())
                connection = getNewConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private Connection getNewConnection() {
        /*String url = "jdbc:sqlite:E:\\Anul2\\Semestrul2\\Medii de proiectare si programare\\labJava\\src\\main\\resources\\SQLite\\database.db";
        String user = "root";
        String password = "secret";
        */
        System.out.println("Getting a connection to database");
        LOGGER.info("--Estabilishing a new connection--");
        String url = properties.getProperty("moto.jdbc.url");
        System.out.println(url);
        String user = properties.getProperty("moto.jdbc.user");
        String password = properties.getProperty("moto.jdbc.password");

        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Error on getting connection on database");
            LOGGER.error("Error on creating new connection!" , e);
        }
        return null;
    }

    //TODO: AICI E PROBLEMA!!!!!!!!

    private void loadProperties() {
        //ClassLoader imi inccarca fisierul dat in obiectul properties
        //ClassLoader classLoader = Application.class.getClassLoader();
        try {
            properties.load(new FileReader(propertiesFileName));
            System.out.println("Properties database loaded@@@");
        } catch (IOException e) {
            System.out.println("EROARE AICI!");
            e.printStackTrace();
        }
    }
}
