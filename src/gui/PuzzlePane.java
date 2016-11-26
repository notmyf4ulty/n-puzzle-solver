package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import model.game.BoardDimension;
import model.game.GameModel;

class PuzzlePane extends VBox {

    private GameModel gameModel;
    StackPane puzzleBoardPane;

    PuzzlePane() {
        puzzleBoardPane = new StackPane();
        puzzleBoardPane.setMinHeight(310);
        puzzleBoardPane.setMinWidth(310);
        puzzleBoardPane.setAlignment(Pos.CENTER);
        puzzleBoardPane.setStyle("-fx-border-width: 1; -fx-border-style: solid;");
        puzzleBoardPane.getChildren().add(new PuzzleBoardGrid().getMainPane());
        Button solveButton = createSolveButton();
        Button meshButton = createMeshButton();
        GridPane boardDimensionRadioGroupPane = createBoardDimensionRadioGroupPane();
        getChildren().add(puzzleBoardPane);
        getChildren().add(solveButton);
        getChildren().add(meshButton);
        getChildren().add(boardDimensionRadioGroupPane);
        setPadding(new Insets(5,5,5,5));
        gameModel = GameModel.getInstance();
    }

    private Button createSolveButton() {
        Button button = new Button("Click me");
        button.setOnAction(actionEvent -> gameModel.solveSinglePuzzle());
        return button;
    }

    private Button createMeshButton() {
        Button button = new Button("Mesh");
        button.setOnAction(actionEvent -> gameModel.mesh());
        return button;
    }

    private GridPane createBoardDimensionRadioGroupPane() {
        final String x3Dimension = "3x3";
        final String x4Dimension = "4x4";
        final String x5Dimension = "5x5";
        GridPane boardDimensionRadioGroupPane = new GridPane();
        ColumnConstraints firstColumnConstraints = new ColumnConstraints();
        firstColumnConstraints.setPercentWidth(33);
        ColumnConstraints secondColumnConstraints = new ColumnConstraints();
        secondColumnConstraints.setPercentWidth(33);
        ColumnConstraints thirdColumnConstraints = new ColumnConstraints();
        thirdColumnConstraints.setPercentWidth(33);
        boardDimensionRadioGroupPane.getColumnConstraints()
                .addAll(firstColumnConstraints, secondColumnConstraints, thirdColumnConstraints);
        final ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton x3Choice = new RadioButton(x3Dimension);
        x3Choice.setToggleGroup(toggleGroup);
        RadioButton x4Choice = new RadioButton(x4Dimension);
        x4Choice.setToggleGroup(toggleGroup);
        RadioButton x5Choice = new RadioButton(x5Dimension);
        x5Choice.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                GameModel gameModel = GameModel.getInstance();
                RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                switch (selectedRadioButton.getText()) {
                    case x3Dimension:
                        gameModel.changeBoardDimension(BoardDimension.X3_DIMENSION);
                        break;
                    case x4Dimension:
                        gameModel.changeBoardDimension(BoardDimension.X4_DIMENSION);
                        break;
                    case x5Dimension:
                        gameModel.changeBoardDimension(BoardDimension.X5_DIMENSION);
                        break;
                }
                puzzleBoardPane.getChildren().clear();
                puzzleBoardPane.getChildren().add(new PuzzleBoardGrid().getMainPane());
            }
        });
        boardDimensionRadioGroupPane.add(x3Choice,0,0);
        GridPane.setHalignment(x5Choice, HPos.CENTER);
        boardDimensionRadioGroupPane.add(x4Choice,1,0);
        GridPane.setHalignment(x4Choice, HPos.CENTER);
        boardDimensionRadioGroupPane.add(x5Choice,2,0);
        GridPane.setHalignment(x5Choice, HPos.CENTER);
        x3Choice.setSelected(false);
        x3Choice.setSelected(true);

        return boardDimensionRadioGroupPane;
    }
}
