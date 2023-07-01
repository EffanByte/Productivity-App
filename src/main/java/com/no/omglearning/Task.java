package com.no.omglearning;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class Task {
    Task(){

    }
    static Color ColorE = new Color(0,0.7,0, 1);
    static Color ColorM = new Color(0.6,0.5,0,1);
    static Color ColorH = new Color(0.5,0,0,1);
    Task(double XP) {
        this.XP = XP;
    }

    private double XP;
    void completeEasy() throws SQLException {
MyJDBC.increaseXP(2.5);
MyJDBC.increaseLevel();
    }

    void completeMedium() throws SQLException {
MyJDBC.increaseXP(4);
        MyJDBC.increaseLevel();
    }
    void completeHard() throws SQLException {
MyJDBC.increaseXP(7.5 );
        MyJDBC.increaseLevel();
    }

    public double getXP() {
        return XP;
    }
}


