package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import model.game.*;


class SolveTypeSettingsPane extends AnchorPane {

    SolveTypeSettingsPane() {
        StackPane solveTypePane = createSolveSettingsPane();
        getChildren().addAll(solveTypePane);
    }

    private StackPane createSolveSettingsPane() {
        StackPane solveTypePane = new StackPane();
        AnchorPane.setTopAnchor(solveTypePane,5.0);
        AnchorPane.setRightAnchor(solveTypePane,5.0);
        AnchorPane.setLeftAnchor(solveTypePane,5.0);

        GridPane singleSolveSettingsPane = createSingleSolveSettingsPane();
        singleSolveSettingsPane
                .setStyle("-fx-border-color: black; -fx-border-width: 1 1 2 1; -fx-border-style: solid;");
        singleSolveSettingsPane.setPadding(new Insets(5,5,5,5));

        solveTypePane.getChildren().add(singleSolveSettingsPane);
        return solveTypePane;
    }

    private GridPane createSingleSolveSettingsPane() {
        GridPane singleSolveSettingsPane = new GridPane();
        ColumnConstraints firstColumnConstraints = new ColumnConstraints();
        firstColumnConstraints.setPercentWidth(50);
        ColumnConstraints secondColumnConstraints = new ColumnConstraints();
        secondColumnConstraints.setPercentWidth(50);
        singleSolveSettingsPane.getColumnConstraints().addAll(firstColumnConstraints, secondColumnConstraints);
        VBox searchTypeRadioGroupPane = createSearchTypeRadioGroupPane();
        VBox heuristicTypeRadioGroupPane = createHeuristicTypeRadioGroupPane();
        singleSolveSettingsPane.add(searchTypeRadioGroupPane,0,0);
        singleSolveSettingsPane.add(heuristicTypeRadioGroupPane,1,0);
        return singleSolveSettingsPane;
    }

    private VBox createSearchTypeRadioGroupPane() {
        final String aStarSearchType = "A*";
        final String idaStarSearchType = "IDA*";
        VBox searchTypeRadioGroupPane = new VBox();
        final ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton aStarChoice = new RadioButton(aStarSearchType);
        aStarChoice.setToggleGroup(toggleGroup);
        RadioButton idaStarChoice = new RadioButton(idaStarSearchType);
        idaStarChoice.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            GameModel gameModel1 = GameModel.getInstance();
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            switch (selectedRadioButton.getText()) {
                case aStarSearchType:
                    gameModel1.setSearchType(SearchType.A_STAR);
                    break;
                case idaStarSearchType:
                    gameModel1.setSearchType(SearchType.IDA_STAR);
                    break;
            }
        });
        Label searchTypeLabel = new Label("Rodzaj przeszukiwania:");
        searchTypeRadioGroupPane.getChildren().addAll(searchTypeLabel);
        searchTypeRadioGroupPane.getChildren().add(aStarChoice);
        searchTypeRadioGroupPane.getChildren().add(idaStarChoice);
        aStarChoice.setSelected(true);

        return searchTypeRadioGroupPane;
    }

    private VBox createHeuristicTypeRadioGroupPane() {
        final String unorderedBlocksHeuristic = "Nieposegregowane bloczki";
        final String manhattanDistanceBlocksHeuristic = "Metryka Manhattan";
        VBox heuristicTypeRadioGroupPane = new VBox();
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton unsortedBlocksChoice = new RadioButton(unorderedBlocksHeuristic);
        unsortedBlocksChoice.setToggleGroup(toggleGroup);
        RadioButton manhattanDistanceChoice = new RadioButton(manhattanDistanceBlocksHeuristic);
        manhattanDistanceChoice.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            GameModel gameModel1 = GameModel.getInstance();
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            switch (selectedRadioButton.getText()) {
                case unorderedBlocksHeuristic:
                    gameModel1.setHeuristicType(HeuristicType.UNORDERED_BLOCKS);
                    break;
                case manhattanDistanceBlocksHeuristic:
                    gameModel1.setHeuristicType(HeuristicType.MANHATTAN_DISTANCE);
                    break;
            }
        });
        Label heuristicTypeLabel = new Label("Heurystyka:");
        heuristicTypeRadioGroupPane.getChildren().addAll(heuristicTypeLabel);
        heuristicTypeRadioGroupPane.getChildren().add(unsortedBlocksChoice);
        heuristicTypeRadioGroupPane.getChildren().add(manhattanDistanceChoice);
        unsortedBlocksChoice.setSelected(true);

        return heuristicTypeRadioGroupPane;
    }
}
