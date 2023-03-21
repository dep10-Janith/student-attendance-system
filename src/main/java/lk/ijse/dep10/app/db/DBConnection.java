package lk.ijse.dep10.app.db;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;

    private DBConnection() {

//        Properties configurations = new Properties();
//        File configFIle = new File("application.properties");
//        try {
//            FileReader fr = new FileReader(configFIle);
//            configurations.load(fr);
//            fr.close();
//            String host = configurations.getProperty("mysql.database", "localhost");
//            String port = configurations.getProperty("mysql.database", "3306");
//            String database = configurations.getProperty("mysql.database", "dep10_Students");
//            String username = configurations.getProperty("mysql.database", "root");
//            String password = configurations.getProperty("mysql.database", "mysql");
//            String queryString = "createDatabaseIfNotExist=true&allowMultiQueries=true";
//            String url = String.format("jdbc:mysql://%s:%s/%s?%s", host, port, database, queryString);
//            connection = DriverManager.getConnection(url, username, password);
//
//        } catch (FileNotFoundException e) {
//            new Alert(Alert.AlertType.ERROR, "Configuration file doesn't exist").showAndWait();
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            new Alert(Alert.AlertType.ERROR, "Failed to read configurations").showAndWait();
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "Failed to establish the database connection,try again. Else contact a technician");
//            throw new RuntimeException(e);
//        }


        try {
            connection = DriverManager.getConnection("jdbc:mysql://dep10.lk/dep10_manageS", "root", "mysql");
        } catch (SQLException e) {

            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to obtain the database connection").showAndWait();
            System.exit(1);
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getDbConnection() {
        return dbConnection;
    }

    public static void setDbConnection(DBConnection dbConnection) {
        DBConnection.dbConnection = dbConnection;
    }

    public static DBConnection getInstance() {
        return (dbConnection == null) ? dbConnection = new DBConnection() : dbConnection;

    }

    public Connection getConnection() {
        return connection;
    }


}