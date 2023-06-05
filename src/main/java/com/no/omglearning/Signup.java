package com.no.omglearning;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Signup {
    @FXML
    TextField username, hobbies, fos;
    @FXML
    Label warn, userwarn, fillout;
    @FXML
    PasswordField pass, confirmpass;

    public void signedup(ActionEvent e) throws IOException, SQLException {
        Connection warnings = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "Starbound");
        if (!(username.getText().isEmpty()
                | hobbies.getText().isEmpty() |
                pass.getText().isEmpty() |
                confirmpass.getText().isEmpty() | fos.getText().isEmpty()) ) {
            fillout.setVisible(false);
        if (Objects.equals(pass.getText(), confirmpass.getText()) & checkuser(warnings, username.getText())) {
                createUser(username.getText());
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + username.getText(), "root", "Starbound");
                String sql = "INSERT INTO userdata (Username, Level, XP, Hobbies, Field_of_Study, Password) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, username.getText());
                stmt.setInt(2, 1);
                stmt.setInt(3, 0);
                stmt.setString(4, hobbies.getText());
                stmt.setString(5, fos.getText());
                stmt.setString(6, pass.getText());
                stmt.executeUpdate();
                Controller.switchtologin(e);
                connection.close();
            }      else {
            if (!Objects.equals(pass.getText(), confirmpass.getText())) {
                warn.setVisible(true);
            }
            else
                warn.setVisible(false);
            if (!checkuser(warnings, username.getText())){
                userwarn.setVisible(true);
            }
            else
                warn.setVisible(false);
        }
        }
        else
            fillout.setVisible(true);
    }

    public void back(ActionEvent e) throws IOException {
        Controller.switchtologin(e);
    }




public void createUser(String user){
    String existingSchema = "myshop";

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "Starbound")) {
        createSchema(connection, user); // lets

        copyTableStructure(connection, existingSchema, user); //
        System.out.println("Schema created.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    private static void createSchema(Connection connection, String schemaName) throws SQLException {
        String createSchemaQuery = "CREATE SCHEMA " + schemaName;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createSchemaQuery);
        }
    }

    private static void copyTableStructure(Connection connection, String existingSchema, String newSchema)
            throws SQLException {
        String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'myshop'";
         Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                String createTableQuery = "CREATE TABLE " + newSchema + "." + tableName + " LIKE " +
                        existingSchema + "." + tableName;
                try (Statement createStatement = connection.createStatement()) {
                    createStatement.executeUpdate(createTableQuery);
                }
            }
    }
private static boolean checkuser(Connection connection, String username) throws SQLException {
    String query = "SHOW DATABASES";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(query);
    while (resultSet.next()) {
        if (Objects.equals(username, resultSet.getString("Database"))) {
return false;
        }
    }
return true;
    }
}