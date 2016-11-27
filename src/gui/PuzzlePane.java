package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

/**
 * Pole przechowujące graficzną reprezentację układanki.
 */
class PuzzlePane extends VBox {

    /**
     * Pole do przechowania.
     */
    private StackPane puzzleBoardPane;

    /**
     * Domyślny konstruktor. Tworzy graficzną reprezentację układanki i wyświetla ją na ekranie.
     */
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

    /**
     * Getter graficznej reprezentacji układanki.
     * @return Graficzna reprezentacja układanki.
     */
    StackPane getPuzzleBoardPane() {
        return puzzleBoardPane;
    }
}
