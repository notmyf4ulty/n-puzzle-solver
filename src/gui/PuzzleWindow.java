package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.game.GameModel;
import model.PuzzleBoardModel;

public class PuzzleWindow extends HBox {

    AnchorPane puzzleBoardPane;
    VBox puzzlePane;
    PuzzleBoardGrid puzzleBoardGrid;
    PuzzleBoardModel puzzleBoardModel;
    GameModel gameModel;
    Button solveButton;
    Button meshButton;

    public PuzzleWindow() {
        puzzlePane = new VBox();
        puzzleBoardPane = new AnchorPane();
        puzzleBoardPane.getChildren().add(new PuzzleBoardGrid().getMainPane());
        solveButton = createSolveButton();
        meshButton = createMeshButton();
        puzzlePane.getChildren().add(puzzleBoardPane);
        puzzlePane.getChildren().add(solveButton);
        puzzlePane.getChildren().add(meshButton);
        getChildren().add(puzzlePane);
        getChildren().add(new SettingsPane());
        gameModel = GameModel.getInstance();
        puzzleBoardModel = gameModel.getPuzzleBoardModel();
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
