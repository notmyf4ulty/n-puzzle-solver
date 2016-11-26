package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.game.GameModel;

class PuzzlePane extends VBox {

    private GameModel gameModel;

    PuzzlePane() {
        AnchorPane puzzleBoardPane = new AnchorPane();
        puzzleBoardPane.getChildren().add(new PuzzleBoardGrid().getMainPane());
        Button solveButton = createSolveButton();
        Button meshButton = createMeshButton();
        getChildren().add(puzzleBoardPane);
        getChildren().add(solveButton);
        getChildren().add(meshButton);
        gameModel = GameModel.getInstance();
    }

    private Button createSolveButton() {
        Button button = new Button("Click me");
        button.setOnAction(actionEvent -> gameModel.solvePuzzle());
        return button;
    }

    private Button createMeshButton() {
        Button button = new Button("Mesh");
        button.setOnAction(actionEvent -> gameModel.mesh());
        return button;
    }
}
