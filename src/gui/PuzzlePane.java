package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

class PuzzlePane extends VBox {

    private StackPane puzzleBoardPane;

    PuzzlePane() {
        puzzleBoardPane = new StackPane();
        puzzleBoardPane.setMinHeight(310);
        puzzleBoardPane.setMinWidth(310);
        puzzleBoardPane.setAlignment(Pos.CENTER);
        puzzleBoardPane.setStyle("-fx-border-width: 1; -fx-border-style: solid;");
        puzzleBoardPane.getChildren().add(new PuzzleBoardGrid().getMainPane());
        getChildren().add(puzzleBoardPane);
        setPadding(new Insets(5,5,5,5));
    }

    StackPane getPuzzleBoardPane() {
        return puzzleBoardPane;
    }
}
