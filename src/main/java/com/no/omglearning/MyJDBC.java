package com.no.omglearning;
import javafx.scene.paint.Color;
import java.sql.*;
import java.util.ArrayList;

import static com.no.omglearning.Login.Uname;

public class MyJDBC extends Task{

    public static ArrayList<String> DBtolist() throws SQLException {
        ArrayList<String> tasks = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Uname, "root", "Starbound");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from todo");
        while (resultSet.next()){
            tasks.add(resultSet.getString("task"));
        }
return tasks;
    }
    public static ArrayList<Color> addcolor() throws SQLException{
        ArrayList<Color> colors = new ArrayList<>();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Uname, "root", "Starbound");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from todo");
        while (resultSet.next()){
            if (resultSet.getInt("Difficulty") == 1)
                colors.add(ColorE);
            if (resultSet.getInt("Difficulty") == 2)
                colors.add(ColorM);
            if (resultSet.getInt("Difficulty") == 3)
                colors.add(ColorH);
        }
        return colors;
    }
    public static void tasktoDB(int diff, String task) throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Uname, "root", "Starbound");
        String sql = "INSERT INTO todo (Difficulty, Task) VALUES (?, ?)";
       PreparedStatement stmt = connection.prepareStatement(sql);
stmt.setInt(1, diff);
stmt.setString(2, task);
stmt.executeUpdate();
        System.out.println(diff);
        System.out.println(task);
    }
    public static void deletetask(int num) throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Uname, "root", "Starbound");
        PreparedStatement  stmt = connection.prepareStatement("DELETE FROM todo WHERE row_num = ?");
        stmt.setInt(1, num);
        stmt.executeUpdate();
        connection.close();
    }

        public static void resetRowNumber() throws SQLException {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Uname, "root", "Starbound");
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE todo SET row_num = (SELECT @row_number := @row_number + 1 FROM (SELECT @row_number := -1) AS t)"
                );
                statement.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error resetting row number: " + e.getMessage());
            }

            }
            public static ArrayList<String> stats() throws SQLException {
        ArrayList<String> stats  = new ArrayList<>();
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Uname, "root", "Starbound");
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("select * from userdata");
                rs.next();
                stats.add(rs.getString("Username"));
                stats.add(String.valueOf(rs.getInt("Level")));
                stats.add(String.valueOf(rs.getDouble("XP")));
                stats.add(rs.getString("Hobbies"));
                stats.add(rs.getString("Field_of_Study"));
                connection.close();
                return stats;
            }
public static void increaseXP(double increase) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Uname, "root", "Starbound");
    PreparedStatement  stmt = connection.prepareStatement("SELECT XP FROM " + Uname + ".userdata");
    ResultSet RS = stmt.executeQuery();
    RS.next();
    double inc = RS.getInt(1) + increase;
    PreparedStatement statement = connection.prepareStatement("UPDATE " + Uname + ".userdata SET XP = " + inc);
    statement.executeUpdate();
    connection.close();
}
public static void increaseLevel() throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Uname, "root", "Starbound");
    PreparedStatement  stmt = connection.prepareStatement("SELECT XP FROM " + Uname + ".userdata");
    ResultSet RS = stmt.executeQuery();
    RS.next();
    int level = 0;
    double XP = RS.getDouble(1);
    if (XP < 10)
        level = 1;
    else if (10 < XP & XP < 50)
        level = 2;
    else if (50 < XP & XP < 200)
        level = 4;
    else if (200 < XP & XP < 500)
        level = 5;
    else if (500 < XP & XP < 1000)
        level = 6;
    else if (1000 < XP & XP < 2000)
        level = 7;
    else if (2000 < XP & XP < 5000)
        level = 8;
    else if (5000 < XP & XP < 10000)
        level = 9;
    else if (10000 < XP)
        level = 10;
    PreparedStatement up = connection.prepareStatement("UPDATE " + Uname + ".userdata SET Level = " + level);
    up.executeUpdate();
    connection.close();
}

    }