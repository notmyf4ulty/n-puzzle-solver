package gui;

import javafx.scene.layout.HBox;

public class MainPane extends HBox {

    public MainPane() {
        getChildren().add(new PuzzlePane());
        getChildren().add(new SettingsPane());
    }
}
