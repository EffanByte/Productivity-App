package com.no.omglearning;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.no.omglearning.Model.loadModel;

public class Alarm {
    static Scene scene;
    public static Scene alarm(){
        ToggleButton Pom = new ToggleButton("Start Pom Timer");
            TextField minbox = new TextField("2");
            minbox.setPromptText("Enter Study Minutes here");
            Group alarm = loadModel(Alarm.class.getResource("alarm4.obj"));
            alarm.setScaleY(5);
            alarm.setScaleX(5);
            alarm.setScaleZ(5);
            Bounds bounds = alarm.getBoundsInParent();
            double centerX = bounds.getCenterX();
            double centerY = bounds.getCenterY();
            double centerZ = bounds.getCenterZ();
            Transform linecenter = new Translate(centerX - 165, centerY - 160, centerZ);
            Rotate alarmrotate = new Rotate(90, new Point3D(0, 1, 0));
            alarm.getTransforms().add(alarmrotate);
            Group root = new Group(alarm);
            Line minuteHand = new Line(200, 200, 200, 120);
            minuteHand.setStroke(Color.BLACK);
            minuteHand.setStrokeWidth(4);

            Line secondHand = new Line(200, 200, 200, 100);
            secondHand.setStroke(Color.RED);
            secondHand.setStrokeWidth(2);
        secondHand.getTransforms().add(linecenter);
        minuteHand.getTransforms().add(linecenter);
        root.getChildren().add(secondHand);
        root.getChildren().add(minuteHand);
        Button alarmback = new Button("Back");
            Label displayTime = new Label();
            displayTime.setScaleX(3);
            displayTime.setBackground(Background.fill(Color.WHITE));
            displayTime.setScaleY(2);
            Pom.setOnMouseClicked(Event -> {

                PomTimer timer = new PomTimer();
                int text = Integer.parseInt(minbox.getText());
                try {
                    timer.setMinute(text - 1);
                } catch (NumberFormatException ignored) {
                }
                Rotate minuterot = new Rotate(0, secondHand.getStartX(), secondHand.getStartY(), 0, Rotate.Z_AXIS);
                minuteHand.getTransforms().add(minuterot);
                Rotate secondrotation = new Rotate(0, secondHand.getStartX(), secondHand.getStartY());
                secondHand.getTransforms().add(secondrotation);
                Rotate minuterotation = new Rotate(0, minuteHand.getStartX(), minuteHand.getStartY());
                minuteHand.getTransforms().add(minuterotation);
                Timeline secondTimeline = new Timeline(new KeyFrame(Duration.seconds(60),
                        new KeyValue(secondrotation.angleProperty(), 360)));
                Timeline minuteTimeline = new Timeline(new KeyFrame(Duration.seconds(text * 60),
                        new KeyValue(minuterotation.angleProperty(), 360)));
                secondTimeline.setCycleCount(text);
                minuteTimeline.setCycleCount(1);

                secondTimeline.setOnFinished(e -> {
                    secondTimeline.stop();
                    secondTimeline.playFromStart();
                });

                minuteTimeline.setOnFinished(e -> {
                    minuteTimeline.stop();
                    minuteTimeline.playFromStart();
                });

                secondTimeline.play();
                minuteTimeline.play();

                ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
                executor.scheduleAtFixedRate(() -> Platform.runLater(() -> {
                    if (timer.getMinute() == -1){
                        minuteTimeline.stop();
                        secondTimeline.stop();
                        minuteTimeline.playFromStart();
                        secondTimeline.playFromStart();
                        timer.interrupt();
                        displayTime.setVisible(false);

                            if (!Objects.equals(minbox.getPromptText(), "Enter BREAK minutes here")) {
                                try {
                                    MyJDBC.increaseXP(Double.parseDouble(String.valueOf(text)) * 0.1);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }

                                minbox.clear();
                                minbox.setPromptText("Enter BREAK minutes here");
                            }
                            else {
                                minbox.setPromptText("Enter Study Minutes here");
                            }
                            executor.close();
                            minbox.clear();
                        Pom.setDisable(false);
                        minbox.setDisable(false);
                        displayTime.setVisible(true);
                    }
                    else {
                        if (timer.getSecond() > 0 & timer.getSecond() < 10)
                        {
                            displayTime.setText(timer.getMinute() + ":" + "0" + timer.getSecond());
                        }
                        else
                            displayTime.setText(timer.getMinute() + ":" + timer.getSecond());
                    }
                }), 0, 1, TimeUnit.SECONDS);
                timer.start();
                Pom.setDisable(true);
                minbox.setDisable(true);
            });

            alarmback.setOnAction(event -> {
                try {
Controller.switchtoModel(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            VBox vbox = new VBox(root, Pom, minbox, displayTime ,alarmback);
            scene = new Scene(vbox, 800, 800, false, SceneAntialiasing.BALANCED);
            minbox.setMaxSize(scene.getWidth() / 6, scene.getHeight() / 24);
            vbox.setSpacing(10);
            vbox.setBackground(new Background(new BackgroundFill(Gradiant(), null, null)));
            vbox.setAlignment(Pos.CENTER);
            return scene;
        }


    public static LinearGradient Gradiant() {
        // Define the stops for the linear gradient
        Stop[] stops = new Stop[]{
                new Stop(0, Color.LIGHTBLUE),
                new Stop(0.5, Color.BLUE),
                new Stop(1, Color.DARKBLUE)
        };

        // Create the linear gradient
        return new LinearGradient(0, 0, 0, 1, true, null, stops);
    }

}
