package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.game.GameModel;

public class MainPane extends VBox {
    GameModel gameModel;

    public MainPane() {
        gameModel = GameModel.getInstance();
        HBox puzzleSettingsPane = new HBox();
        puzzleSettingsPane.getChildren().add(new PuzzlePane());
        puzzleSettingsPane.getChildren().add(new SettingsPane());
        Button solveButton = createSolveButton();
        getChildren().addAll(puzzleSettingsPane,solveButton);
    }

    private Button createSolveButton() {
        Button button = new Button("Rozwiąż");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnAction(actionEvent -> gameModel.solveSinglePuzzle());
        return button;
    }
}
