package com.no.omglearning;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import static com.no.omglearning.Alarm.alarm;
import static com.no.omglearning.Model.model;

public class Controller extends Application {
    private static Stage stage = new Stage();
    private static Scene scene;
    static Stage animationstage = new Stage();
    static Image image = new Image(Objects.requireNonNull(Controller.class.getResource("book.png")).toExternalForm());

    public static void switchtoTodo(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("SceneB.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
        scene = new Scene(root);
        stage.setScene(scene);
        playing();
        stage.show();
        stage.setTitle("Effan's Todo List");
    }

    public static void switchtoModel(ActionEvent event) throws IOException {
        try {
            if (((Node) event.getSource()).getScene() == Alarm.scene) {
            } else
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            animationstage.hide();
            stage.setResizable(true);
            playing();
            stage.setScene(model());
            stage.setTitle("Effan's Productivity App");
            stage.getIcons().add(image);
            stage.show();
        } catch (NullPointerException ignored){

        }
    }
    public static void switchtodata(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("Data.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        playing();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Stats");
    }

    public void start(Stage stage) throws Exception {
        createalarmstage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("login.fxml")));
            scene = new Scene(root);
            stage.getIcons().add(image);
            stage.setScene(scene);
        stage.show();
        stage.setTitle("Effan's Productivity App");

    }
    public static void switchtoalarm(MouseEvent event) throws IOException, InterruptedException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        playing();
        animationstage.show();
        stage.setTitle("Effan's Pomodoro Timer");
    }
public static void createalarmstage() {
        animationstage.setTitle("Effan Pomodoro Timer");
        animationstage.hide();
        animationstage.setScene(alarm());

  animationstage.getIcons().add(image);
}
public static void switchtosignup(ActionEvent e) throws IOException {
    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    Parent root = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("Signup.fxml")));
    scene = new Scene(root);
    stage.setScene(scene);
}
public static void switchtologin(Event e) throws IOException {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    Parent root = FXMLLoader.load(Objects.requireNonNull(Controller.class.getResource("login.fxml")));
    scene = new Scene(root);
    stage.setScene(scene);
}
public static void playing(){
        if (AudioPlayer.playing){
            AudioPlayer.play();
            AudioPlayer.playing = true;
        }
        else {
            AudioPlayer.pause();
            AudioPlayer.playing = false;

        }
}
}
