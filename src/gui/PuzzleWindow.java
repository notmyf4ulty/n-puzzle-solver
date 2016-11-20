package gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class PuzzleWindow {

    Scene scene;
    AnchorPane anchorPane;
    PuzzleBoard puzzleBoard;

    public PuzzleWindow() {
        anchorPane = new AnchorPane();
        puzzleBoard = new PuzzleBoard();
        anchorPane.getChildren().add(puzzleBoard.getMainPane());
        scene = new Scene(anchorPane);
    }

    public Scene getScene() {
        return scene;
    }
}
