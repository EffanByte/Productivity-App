package com.no.omglearning;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import javafx.scene.text.*;

public  class Checkbox extends Task implements Initializable {
    private int difficulty = 1;
    @FXML
    private Label lebel1, lebel2, lebel3, lebel4, lebel5, lebel6, lebel7, lebel8, lebel9;
    @FXML
    private CheckBox tick1, tick2, tick3, tick4, tick5, tick6, tick7, tick8, tick9;
    @FXML
    private Button Trash1, Trash2, Trash3, Trash4, Trash5, Trash6, Trash7, Trash8, Trash9;

    @FXML
    private TextField TaskEnter;

    @FXML
    void DispText() throws SQLException {
        labelText();
        checkbox();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            MyJDBC.resetRowNumber();
            ArrayList<String> tasks = MyJDBC.DBtolist();
            ArrayList<Color> colors = MyJDBC.addcolor();
            List<Label> labelList = Arrays.asList(lebel1, lebel2, lebel3, lebel4, lebel5, lebel6, lebel7, lebel8, lebel9);
            for (String s : tasks) {
                if (tasks.indexOf(s) == 9)
                    break;
                Text t = new Text(s);
                if (t.getLayoutBounds().getWidth() > 250)
                    labelList.get(tasks.indexOf(s)).setFont(new Font("Forte", 16));
                labelList.get(tasks.indexOf(s)).setText(s);
            }
            for (int i = 0; i < colors.size(); i++) {
                if (i == 9 )
                    break;
                labelList.get(i).setTextFill(colors.get(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Controller.switchtoModel(event);
    }

    @FXML
    void setEasy() {
        difficulty = 1;
    }

    @FXML
    void setMedium() {
        difficulty = 2;
    }

    @FXML
    void setHard() {
        difficulty = 3;
    }

    @FXML
    void checkbox() {
        List<Label> labelList = Arrays.asList(lebel1, lebel2, lebel3, lebel4, lebel5, lebel6, lebel7, lebel8, lebel9);
        List<CheckBox> checkList = Arrays.asList(tick1, tick2, tick3, tick4, tick5, tick6, tick7, tick8, tick9);
        for (Label label : labelList) {

            if (!label.getText().isEmpty())
                checkList.get(labelList.indexOf(label)).setVisible(true);
        }
    }

    @FXML
    void complete() throws SQLException {
        List<CheckBox> checkList = Arrays.asList(tick1, tick2, tick3, tick4, tick5, tick6, tick7, tick8, tick9);
        List<Label> labelList = Arrays.asList(lebel1, lebel2, lebel3, lebel4, lebel5, lebel6, lebel7, lebel8, lebel9);
        for (CheckBox check : checkList) {
            if (check.isSelected() & !labelList.get(checkList.indexOf(check)).getText().isEmpty()) {
                labelList.get(checkList.indexOf(check)).setText("");
                if (labelList.get(checkList.indexOf(check)).getTextFill() == ColorE) {
                    completeEasy();
                    MyJDBC.deletetask(checkList.indexOf(check));
                }
                if (labelList.get(checkList.indexOf(check)).getTextFill() == ColorM) {
                    completeMedium();
                    MyJDBC.deletetask(checkList.indexOf(check));
                }
                if (labelList.get(checkList.indexOf(check)).getTextFill() == ColorH) {
                    completeHard();
                    MyJDBC.deletetask(checkList.indexOf(check));
                }

                System.out.println("The current XP is " + getXP());
            }
        }
    }

    @FXML
    void labelText() throws SQLException {
        String taskInput = TaskEnter.getText();
        List<Label> labelList = Arrays.asList(lebel1, lebel2, lebel3, lebel4, lebel5, lebel6, lebel7, lebel8);
        for (Label label : labelList) {

            if (label.getText().isBlank()) {
                if (difficulty == 1) {
                    label.setTextFill(ColorE);

                }
                if (difficulty == 2) {
                    label.setTextFill(ColorM);
                }
                if (difficulty == 3) {
                    label.setTextFill(ColorH);
                }
                MyJDBC.tasktoDB(difficulty, taskInput);
                label.setText(taskInput);
                MyJDBC.resetRowNumber();
                break;
            }

        }


        TaskEnter.clear();

    }

    @FXML
    void trash() {
        List<Button> Trashlist = Arrays.asList(Trash1, Trash2, Trash3, Trash4, Trash5, Trash6, Trash7, Trash8, Trash9);
        List<Label> labelList = Arrays.asList(lebel1, lebel2, lebel3, lebel4, lebel5, lebel6, lebel7, lebel8, lebel9);
        for (Button trash : Trashlist) {
            trash.setOnAction(event -> {
                labelList.get(Trashlist.indexOf(trash)).setText("");
                labelList.get(Trashlist.indexOf(trash)).setTextFill(Color.BLACK);
                try {
                    MyJDBC.deletetask(Trashlist.indexOf(trash));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}


