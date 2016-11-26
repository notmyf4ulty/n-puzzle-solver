package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import model.game.*;


class SolveTypeSettingsPane extends AnchorPane {
    private HBox singleSolveSettingsPane;

    SolveTypeSettingsPane() {
        GridPane solveTypePane = createSolveTypePane();
        getChildren().addAll(solveTypePane);
    }

    private GridPane createSolveTypePane() {
        GridPane meshTypePane = new GridPane();
        RowConstraints firstRowConstraints = new RowConstraints();
        firstRowConstraints.setPercentHeight(30);
        RowConstraints secondRowConstraints = new RowConstraints();
        secondRowConstraints.setPercentHeight(70);
        meshTypePane.getRowConstraints().addAll(firstRowConstraints,secondRowConstraints);
        AnchorPane.setTopAnchor(meshTypePane,5.0);
        AnchorPane.setRightAnchor(meshTypePane,5.0);
        AnchorPane.setBottomAnchor(meshTypePane,5.0);
        AnchorPane.setLeftAnchor(meshTypePane,5.0);

        VBox solveTypeChoicePane = new VBox();
        solveTypeChoicePane.setAlignment(Pos.CENTER);
        Label meshTypeLabel = new Label("Rodzaj rozwiązania:");
        singleSolveSettingsPane = createSingleSolveSettingsPane();
        HBox solveTypeRadioGroupPane = createSolveTypeRadioGroupPane();
        solveTypeChoicePane.getChildren().add(meshTypeLabel);
        solveTypeChoicePane.getChildren().add(solveTypeRadioGroupPane);

        meshTypePane.add(solveTypeChoicePane,0,0);
        meshTypePane.add(singleSolveSettingsPane,0,1);
        return meshTypePane;
    }

    private HBox createSolveTypeRadioGroupPane() {
        final String singleSolveType = "Pojedyncze";
        final String fullSolveType = "Pełna kombinacja";
        HBox solveTypeRadioGroupPane = new HBox();
        final ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton singleChoice = new RadioButton(singleSolveType);
        singleChoice.setToggleGroup(toggleGroup);
        RadioButton fullChoice = new RadioButton(fullSolveType);
        fullChoice.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            GameModel gameModel1 = GameModel.getInstance();
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            switch (selectedRadioButton.getText()) {
                case singleSolveType:
                    gameModel1.setSolveType(SolveType.SINGLE);
                    singleSolveSettingsPane.setDisable(false);
                    break;
                case fullSolveType:
                    gameModel1.setSolveType(SolveType.FULL);
                    singleSolveSettingsPane.setDisable(true);
                    break;
            }
        });
        solveTypeRadioGroupPane.getChildren().add(singleChoice);
        solveTypeRadioGroupPane.getChildren().add(fullChoice);
        singleChoice.setSelected(false);
        singleChoice.setSelected(true);

        return solveTypeRadioGroupPane;
    }

    private HBox createSingleSolveSettingsPane() {
        HBox singleSolveSettingsPane = new HBox();
        VBox searchTypeRadioGroupPane = createSearchTypeRadioGroupPane();
        VBox heuristicTypeRadioGroupPane = createHeuristicTypeRadioGroupPane();
        singleSolveSettingsPane.getChildren().addAll(searchTypeRadioGroupPane, heuristicTypeRadioGroupPane);
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
        heuristicTypeRadioGroupPane.getChildren().add(unsortedBlocksChoice);
        heuristicTypeRadioGroupPane.getChildren().add(manhattanDistanceChoice);
        unsortedBlocksChoice.setSelected(true);

        return heuristicTypeRadioGroupPane;
    }
}
