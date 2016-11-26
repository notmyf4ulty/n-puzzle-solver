package gui;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.game.Block;
import model.game.GameModel;
import model.game.Position;
import model.game.PuzzleBoardModel;
import java.util.Observable;
import java.util.Observer;

class PuzzleBoardGrid implements Observer {
    private AnchorPane mainPane;
    private GridPane board;
    private PuzzleBoardModel puzzleBoardModel;

    PuzzleBoardGrid() {
        puzzleBoardModel = GameModel.getInstance().getPuzzleBoardModel();
        puzzleBoardModel.addObserver(this);
        mainPane = new AnchorPane();
        board = generateNewBoard();
        mainPane.getChildren().add(board);
    }

    private GridPane generateNewBoard() {
        GridPane board = new GridPane();
        Block[][] boardModel = puzzleBoardModel.getBoard();
        for (Block [] blocks : boardModel) {
            for (Block block : blocks) {
                FieldPane fieldPane = new FieldPane(block.getNumber());
                board.add(fieldPane,block.getPosition().getX(),block.getPosition().getY());
            }
        }
        return board;
    }

    private void refreshBoard() {
        Block[][] boardModel = puzzleBoardModel.getBoard();
        for (Block[] blocks : boardModel) {
            for (Block block : blocks) {
                FieldPane fieldPane = getFieldPane(block.getPosition());
                if (fieldPane != null) {
                    fieldPane.setNumber(block.getNumber());
                }
            }
        }
    }

    private FieldPane getFieldPane(Position position) {
        int columnIndex = position.getX();
        int rowIndex = position.getY();
        for (Node node : board.getChildren()) {
            if (GridPane.getRowIndex(node) == rowIndex &&
                    GridPane.getColumnIndex(node) == columnIndex &&
                    node.getClass().equals(FieldPane.class)) {
                return (FieldPane) node;
            }
        }
        return null;
    }


    @Override
    public void update(Observable observable, Object o) {
        System.out.println("Updatin.");
        refreshBoard();
    }

    AnchorPane getMainPane() {
        return mainPane;
    }
}
