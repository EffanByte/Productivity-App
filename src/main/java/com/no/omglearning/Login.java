package com.no.omglearning;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

import static com.no.omglearning.Controller.switchtoModel;

public class Login {
    @FXML
    TextField user;
@FXML
    PasswordField pass;
static String Uname = "";
    public void login(ActionEvent e) throws SQLException, IOException {
        Uname = user.getText();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop", "root", "Starbound");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from " + user.getText() +".userdata");
        while (resultSet.next()) {
            if (Objects.equals(user.getText(), resultSet.getString("Username")) && Objects.equals(pass.getText(), resultSet.getString("Password"))){
                switchtoModel(e);
            }

        }

        connection.close();
    }
    public void signup(ActionEvent e) throws IOException {
        Controller.switchtosignup(e);
    }
}
