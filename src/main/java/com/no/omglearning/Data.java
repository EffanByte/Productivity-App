package com.no.omglearning;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {

    @FXML
    private Button back;
@FXML
private Label user, level, xp, hobbies, fos;
    @FXML
    void Back(ActionEvent event) throws IOException {
Controller.switchtoModel(event);
    }
    public void initialize() throws SQLException {
        ArrayList<String> stats = MyJDBC.stats();
        List<Label> fields = Arrays.asList(user,level,xp,hobbies,fos);
        for (String s: stats){
fields.get((stats.indexOf(s))).setText(s);
        }
    }
}
