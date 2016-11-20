package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.PuzzleBoardModel;

import java.util.Observable;
import java.util.Observer;

public class PuzzleBoard implements Observer {
    private static final int FIELD_DIMENSION = 60;
    AnchorPane mainPane;
    GridPane board;
    PuzzleBoardModel puzzleBoardModel;

    public PuzzleBoard(PuzzleBoardModel puzzleBoardModel) {
        mainPane = new AnchorPane();
        board = generateNewBoard();
        mainPane.getChildren().add(board);
        this.puzzleBoardModel = puzzleBoardModel;
        this.puzzleBoardModel.addObserver(this);
    }

    private GridPane generateNewBoard() {
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

    private void refreshBoard() {
        board = generateNewBoard();
    }

    @Override
    public void update(Observable observable, Object o) {

    }

    public AnchorPane getMainPane() {
        return mainPane;
    }
}
