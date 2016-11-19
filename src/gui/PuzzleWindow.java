package gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Created by przemek on 20.11.16.
 */
public class PuzzleWindow {

    Scene scene;
    AnchorPane anchorPane;
    Label label;

    public PuzzleWindow() {
        label = new Label("Hello");
        anchorPane = new AnchorPane();
        anchorPane.getChildren().add(label);
        scene = new Scene(anchorPane);
    }

    public Scene getScene() {
        return scene;
    }
}
