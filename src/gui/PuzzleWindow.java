package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.game.GameModel;
import model.PuzzleBoardModel;

public class PuzzleWindow {

    Scene scene;
    AnchorPane puzzleBoardPane;
    HBox mainPane;
    VBox vBox;
    HBox hBox;
    PuzzleBoardGrid puzzleBoardGrid;
    PuzzleBoardModel puzzleBoardModel;
    GameModel gameModel;
    Button solveButton;

    public PuzzleWindow() {
        mainPane = new HBox();
        vBox = new VBox();
        puzzleBoardPane = new AnchorPane();
        puzzleBoardPane.getChildren().add(new PuzzleBoardGrid().getMainPane());
        solveButton = createSolveButton();
        vBox.getChildren().add(puzzleBoardPane);
        vBox.getChildren().add(solveButton);
        mainPane.getChildren().add(vBox);
        mainPane.getChildren().add(new SettingsPane());
        scene = new Scene(mainPane);
        gameModel = GameModel.getInstance();
        puzzleBoardModel = gameModel.getPuzzleBoardModel();
    }

    private Button createSolveButton() {
        Button button = new Button("Click me");
        button.setOnAction(actionEvent -> gameModel.solvePuzzle());
        return button;
    }

    public Scene getScene() {
        return scene;
    }
}
