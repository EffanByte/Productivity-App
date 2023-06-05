package com.no.omglearning;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.geometry.Bounds;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static com.no.omglearning.Alarm.alarm;
import static com.no.omglearning.Controller.animationstage;
import static com.no.omglearning.Controller.switchtologin;

public class Model {
    public static Scene model() throws IOException {

AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, 800, 800, false, SceneAntialiasing.BALANCED);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        Group floor = loadModel(Model.class.getResource("floor2obj.obj"));
        Group wall = loadModel(Model.class.getResource("wall.obj"));
        Group roof = loadModel(Model.class.getResource("roof.obj"));
        Group table = loadModel(Model.class.getResource("table.obj"));
        Group alarm = loadModel(Model.class.getResource("alarm.obj"));
        Group flip = loadModel(Model.class.getResource("switch.obj"));
addTexture(floor, new Image((Objects.requireNonNull(Model.class.getResource("woodtexture.jpg")).openStream())));
Image floorimage = new Image(Objects.requireNonNull(Model.class.getResource("WP2.jpeg")).openStream());
root.setTranslateX(alarm.getTranslateX());
root.setTranslateY(alarm.getTranslateY());
root.setTranslateZ(alarm.getTranslateZ());
//floorimage
//addTexture(wall,);
        alarm.setTranslateX(-1.25);
        alarm.setTranslateY(-1.2);
        alarm.setTranslateZ(2);
        alarm.setCursor(Cursor.HAND);
        alarm.setPickOnBounds(true);
        Group chair = loadModel(Model.class.getResource("chair3.obj"));
Group music = loadModel(Model.class.getResource("musicnote.obj"));
        chair.getTransforms().add(new Rotate(5, new Point3D(0, 1, 0)));
        Rotate chairrot = new Rotate(5, Rotate.Z_AXIS);
        chair.getTransforms().add(chairrot);
        chair.setScaleZ(1.2);
        chair.setScaleX(1.2);
        chair.setScaleY(1.2);
        Group todo = loadModel(Model.class.getResource("todo2.obj"));
        Group plant = loadModel(Model.class.getResource("Plant.obj"));
        plant.setOnMouseClicked(e -> {
            try {
                animationstage.setScene(alarm());
                switchtologin(e);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        plant.setCursor(Cursor.HAND);
        todo.setCursor(Cursor.HAND);
        Bounds bounds = todo.getBoundsInLocal();
        Group bookcase = loadModel(Model.class.getResource("Bookcase3.obj"));
        Group modelRoot = new Group(floor, wall, roof, table, alarm, todo, chair, bookcase, plant, flip);
        modelRoot.setTranslateX(10);
        modelRoot.setTranslateZ(2.5);
        modelRoot.setScaleZ(0.4);
        modelRoot.setScaleX(0.4);
        modelRoot.setScaleY(0.4);
        modelRoot.getTransforms().add(new Rotate(20, Rotate.Z_AXIS));
        //    modelRoot.getTransforms().add(new Rotate())
        Rotate modelRot = new Rotate(15, Rotate.Y_AXIS);
        modelRoot.getTransforms().add(modelRot);
        root.getChildren().add(modelRoot);

        //     Transform modelrot = new Rotate(30, new Point3D(1, 0, 0));
        //   Transform modelrot2 = new Rotate(45, new Point3D(0,1,0));
        //    Transform modelrot3 = new Rotate(-36, new Point3D(0, 0, 1));
        //    model.getTransforms().add(modelrot3);
        //    model.getTransforms().add(modelrot);
        //   model.getTransforms().add(modelrot2);
        camera.setTranslateX(15.5);
        camera.setTranslateZ(-4);
        camera.setTranslateY(-9);
        Transform camrot = new Rotate(-40, new Point3D(1, 0, 0));
        Transform camrot2 = new Rotate(-30, new Point3D(0, 1, 0));
        camera.getTransforms().addAll(camrot, camrot2);
        scene.setCamera(camera);
        root.setBackground(new Background(new BackgroundFill(YGradient(), null, null)));
        Group lamp = loadModel(Model.class.getResource("lamp.obj"));
        PhongMaterial material = new PhongMaterial();
        material.setSpecularColor(Color.WHITE);
/*        SpotLight light1 = new SpotLight();
        PhongMaterial material = new PhongMaterial();
        material.setSpecularColor(Color.WHITE);
        modelRoot.setScaleX(0.5);
        modelRoot.setScaleY(0.5);
        modelRoot.setScaleZ(0.5);
        light1.setOuterAngle(180);
        light1.setTranslateX(lamp.getTranslateX());
        light1.setTranslateZ(lamp.getTranslateZ());
        light1.setTranslateY(lamp.getTranslateY());
        light2.setTranslateX(lamp.getTranslateX());

 */
SpotLight light2 = new SpotLight();
        light2.setTranslateY(chair.getTranslateY()-0.4);
        light2.setTranslateZ(chair.getTranslateZ()+0.2);
        light2.setTranslateX(chair.getTranslateX()+0.5);
        light2.setInnerAngle(15);
        light2.setOuterAngle(30);
        light2.setTranslateY(-2);
        Point3D lightPosition = new Point3D(light2.getTranslateX()-1, light2.getTranslateY(), light2.getTranslateZ()-2);
        Point3D objectPosition = new Point3D(todo.getTranslateX(), todo.getTranslateY(), todo.getTranslateZ());
        Point3D direction = objectPosition.subtract(lightPosition).normalize();
        light2.setDirection(direction);
        AmbientLight light5 =new AmbientLight();
 PointLight switchon = new PointLight();
 switchon.setTranslateZ(2);

        light5.setTranslateZ(lamp.getTranslateX());
        light5.setTranslateY(lamp.getTranslateY());
        light5.setTranslateX(lamp.getTranslateX());
        light5.setColor(Color.rgb(110,120,106));
        setMaterial(todo, material);
        DirectionalLight light3 = new DirectionalLight();
        light3.setTranslateZ(lamp.getTranslateZ());
        light3.setTranslateX(lamp.getTranslateX());
        light3.setTranslateY(lamp.getTranslateY());
        DirectionalLight light4 = new DirectionalLight();
        light4.setTranslateZ(lamp.getTranslateZ());
        light4.setTranslateX(lamp.getTranslateX());
        light4.setTranslateY(lamp.getTranslateY());
        modelRoot.getChildren().addAll(lamp);
        modelRoot.getChildren().addAll(light2,light5);
modelRoot.getChildren().add(music);
        lamp.setOnMouseClicked(MouseEvent -> {
            if (!light2.isLightOn()) {
                light2.setLightOn(true);
                light5.setLightOn(true);
            }
else
            {
                light2.setLightOn(false);
                light2.setLightOn(false);
            }
        });
     //   light2.setRotate(modelRoot.getRotate());
        todo.setOnMouseClicked(mouseEvent ->
        {
            try {
                Controller.switchtoTodo(mouseEvent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        alarm.setOnMouseClicked(mouseEvent -> {
            try {
                Controller.switchtoalarm(mouseEvent);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        bookcase.setOnMouseClicked(mouseEvent -> {
            try {
                Controller.switchtodata(mouseEvent);
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        });
        music.setOnMouseClicked(mouseEvent -> {
            if (AudioPlayer.playing)
AudioPlayer.pause();
            else
                AudioPlayer.play();
        });
        modelRoot.getChildren().add(switchon);
        scene.setFill(YGradient());
        flip.setOnMouseClicked(MouseEvent -> {
            if (switchon.isLightOn())
            switchon.setLightOn(false);
            else
                switchon.setLightOn(true);
        });
        bookcase.setCursor(Cursor.HAND);
        lamp.setCursor(Cursor.HAND);
        flip.setCursor(Cursor.HAND);
        music.setCursor(Cursor.HAND);
        return scene;
    }
    private static Group loadModel(URL url) {
        Group modelRoot = new Group();
        ObjModelImporter obj = new ObjModelImporter();
        obj.read(url);
        for (MeshView view : obj.getImport()) {
            modelRoot.getChildren().add(view);
        }

        return modelRoot;
    }

    public static LinearGradient YGradient() {
        Stop[] stops = new Stop[]{
                new Stop(1, Color.web("#FFDAB9")),
                new Stop(0.2, Color.web("#FFE4C4")),
                new Stop(0.5, Color.web("#FFF8DC"))
        };
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        return gradient;
    }

    private static void setMaterial(Group group, Material material) {
        for (Node node : group.getChildren()) {
            if (node instanceof Group) {
                // recursively set material on child group
                setMaterial((Group) node, material);
            } else if (node instanceof Shape3D) {
                // set material on individual shape or mesh
                ((Shape3D) node).setMaterial(material);
            }
        }
    }
    public static void addTexture(Group group, Image diffuseMap) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(diffuseMap);

        for (Node node : group.getChildren()) {
            if (node instanceof Shape3D) {
                Shape3D shape = (Shape3D) node;
                shape.setMaterial(material);
            }
        }
    }
}
