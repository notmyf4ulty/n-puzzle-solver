package gui;

import javafx.scene.layout.HBox;

public class PuzzleWindow extends HBox {

    public PuzzleWindow() {
        getChildren().add(new PuzzlePane());
        getChildren().add(new SettingsPane());
    }
}
