/*
package com.no.omglearning;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class ThreeDBoxDemo extends Application {

    private static final int BOX_SIZE = 100;
    private static final int CAMERA_INITIAL_DISTANCE = -1000;
    private static final double CAMERA_INITIAL_X_ANGLE = 0.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 0.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;

    private final Group root = new Group();
    private final Group boxGroup = new Group();
    private final Xform cameraXform = new Xform();
    private final Xform cameraXform2 = new Xform();
    private final Xform cameraXform3 = new Xform();
    private Camera camera;

    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;


    @Override
    public void start(Stage primaryStage) {
        buildBox();
        buildCamera();
        buildScene();
        buildKeyEvents(primaryStage);

        primaryStage.setTitle("3D Box Demo");
        primaryStage.setScene(new Scene(root, 800, 600, true));
        primaryStage.show();
    }

    private void buildBox() {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
        material.setSpecularColor(Color.WHITE);

        Box box = new Box(BOX_SIZE, BOX_SIZE, BOX_SIZE);
        box.setMaterial(material);

        boxGroup.getChildren().add(box);
        root.getChildren().add(boxGroup);
    }

    private void buildCamera() {
        camera = new PerspectiveCamera(true);
        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);

        cameraXform.getChildren().add(camera);
        cameraXform2.getChildren().add(cameraXform);
        cameraXform3.getChildren().add(cameraXform2);
        root.getChildren().add(cameraXform3);

        cameraXform2.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
        cameraXform2.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
    }

    private void buildScene() {
        root.getChildren().add(new AmbientLight(Color.WHITE));
        root.getChildren().add(new PointLight(Color.WHITE));
    }

    private void buildKeyEvents(Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            double modifier = event.isShiftDown() ? 10.0 : 1.0;
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.LEFT) {
                cameraXform.ry.setAngle(cameraXform.ry.getAngle() - 10.0 * modifier);
            } else if (keyCode == KeyCode.RIGHT) {
                cameraXform.ry.setAngle(cameraXform.ry.getAngle() + 10.0 * modifier);
            } else if (keyCode == KeyCode.UP) {
                cameraXform.rx.setAngle(cameraXform.rx.getAngle() - 10.0 * modifier);
            } else if (keyCode == KeyCode.DOWN) {
                cameraXform.rx.setAngle(cameraXform.rx.getAngle() +

                        10.0 * modifier);
            }
        });    stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                    || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
                event.consume();
            }
        });

        scene.setOnMousePressed(event -> {
            mousePosX = event.getSceneX();
            mousePosY = event.getSceneY();
            mouseOldX = event.getSceneX();
            mouseOldY = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
            mousePosX = event.getSceneX();
            mousePosY = event.getSceneY();
            mouseDeltaX = (mousePosX - mouseOldX) / scene.getWidth() * Math.PI * 2;
            mouseDeltaY = (mousePosY - mouseOldY) / scene.getHeight() * Math.PI * 2;
            cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * 180.0 / Math.PI);
            cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * 180.0 / Math.PI);
        });
    }

    public static class Xform extends Group {
        public final Rotate rx = new Rotate(0, Rotate.X_AXIS);
        public final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
        public final Rotate rz = new Rotate(0, Rotate.Z_AXIS);

        public Xform() {
            super();
            getTransforms().addAll(rz, ry, rx);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
