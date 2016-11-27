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

/**
 * Siatka będąca odzwierciedleniem modelu układanki. Implementuje wzorzec projektowy typu "Obserwator" w celu
 * aktualizacji zmian zachodzących w modelu.
 */
class PuzzleBoardGrid implements Observer {

    /**
     * Główne pole przechowujące planszę.
     */
    private AnchorPane mainPane;

    /**
     * Graficzna reprezentacja planszy.
     */
    private GridPane board;

    /**
     * Model układanki, który będzie obserwowany.
     */
    private PuzzleBoardModel puzzleBoardModel;

    /**
     * Domyślny konstruktor. Pobiera model układanki z modelu gry, który następnie przypisany zostanie do
     * graficznej reprezentacji.
     */
    PuzzleBoardGrid() {
        puzzleBoardModel = GameModel.getInstance().getPuzzleBoardModel();
        puzzleBoardModel.addObserver(this);
        mainPane = new AnchorPane();
        board = generateNewBoard();
        mainPane.getChildren().add(board);
    }

    /**
     * Tworzy nową planszę. Pobiera model układanki i dla każdego bloczku tworzy jego graficzną reprezentację.
     * @return Graficzna reprezentacja planszy.
     */
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

    /**
     * Odświeża planszę. Przebiega po wszystkich bloczkach modelu układanki i przypisuje do swoich pól nowe numery.
     */
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

    /**
     * Pobiera pole o odpowiednim numerze.
     * @param position Pozycja pola.
     * @return Pole o danym numerze.
     */
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

    /**
     * Przeciążona metoda interfejsu Observer.
     */
    @Override
    public void update(Observable observable, Object o) {
        refreshBoard();
    }

    /**
     * Pobiera główne pole.
     * @return Główne pole.
     */
    AnchorPane getMainPane() {
        return mainPane;
    }
}
