package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class PuzzleBoard {
    private static final int FIELD_DIMENSION = 60;
    AnchorPane mainPane;
    GridPane board;

    public PuzzleBoard() {
        mainPane = new AnchorPane();
        board = generateBoard();
        mainPane.getChildren().add(board);
    }

    private GridPane generateBoard() {
        GridPane board = new GridPane();
        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                StackPane field = new StackPane();
                field.setMinSize(FIELD_DIMENSION, FIELD_DIMENSION);
                field.setStyle("-fx-border-color: black;");
                Label number = new Label(Integer.toString(i + j + 2));
                number.setStyle("-fx-font-size: 20");
                field.getChildren().add(number);
                board.add(field,i,j);
            }
        }
        return board;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }
}
