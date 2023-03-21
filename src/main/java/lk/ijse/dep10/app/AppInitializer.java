package lk.ijse.dep10.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.dep10.app.db.DBConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("Database connection is about to close");
                if (DBConnection.getInstance().getConnection() != null && !DBConnection.getInstance().getConnection().isClosed()) {
                    DBConnection.getInstance().getConnection().close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }));

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        generateSchemaIfNotExists();


    }

    private void generateSchemaIfNotExists() {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println(connection);
        try {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SHOW TABLES ");
            HashSet<String> tableNameSet = new HashSet<>();
            while (rst.next()) {
                tableNameSet.add(rst.getString(1));

            }
            boolean tableExists = tableNameSet.containsAll(Set.of("Attendance", "Picture", "Student", "User"));
            System.out.println(tableExists);

            if (!tableExists) {
                System.out.println("Schema is about to auto generate");
                stm.execute(readDBScript());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private String readDBScript() {
        InputStream is = getClass().getResourceAsStream("/schema.sql");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            StringBuilder dbScriptBuilder=new StringBuilder();
            while ((line = br.readLine() )!= null) {
                dbScriptBuilder.append(line).append("\n");

            }
            return dbScriptBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
