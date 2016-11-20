package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.Block;
import model.GameModel;
import model.PuzzleBoardModel;

import java.util.Observable;
import java.util.Observer;

public class PuzzleBoard implements Observer {
    private static final int FIELD_DIMENSION = 60;
    AnchorPane mainPane;
    GridPane board;
    PuzzleBoardModel puzzleBoardModel;

    public PuzzleBoard() {
        puzzleBoardModel = GameModel.getInstance().getPuzzleBoardModel();
        this.puzzleBoardModel.addObserver(this);
        mainPane = new AnchorPane();
        board = generateNewBoard();
        mainPane.getChildren().add(board);
    }

    private GridPane generateNewBoard() {
        GridPane board = new GridPane();

        Block[][] boardModel = puzzleBoardModel.getBoard();
        for (Block [] blocks : boardModel) {
            for (Block block : blocks) {
                StackPane field = new StackPane();
                field.setMinSize(FIELD_DIMENSION, FIELD_DIMENSION);
                field.setStyle("-fx-border-color: black;");
                Label number = new Label(Integer.toString(block.getNumber()));
                number.setStyle("-fx-font-size: 20");
                field.getChildren().add(number);
                board.add(field,block.getPosition().getY(),block.getPosition().getX());
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
