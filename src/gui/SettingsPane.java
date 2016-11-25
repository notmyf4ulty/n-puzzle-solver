package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.game.GameModel;
import model.game.HeuristicType;
import model.game.SearchType;

public class SettingsPane extends VBox {

    VBox searchTypeRadioGroupPane;
    VBox heuristicTypeRadioGroupPane;
    MeshTypeSettingsPane meshTypeSettingsPane;

    GameModel gameModel;

    public SettingsPane() {
        gameModel = GameModel.getInstance();
        searchTypeRadioGroupPane = createSearchTypeRadioGroupPane();
        heuristicTypeRadioGroupPane = createHeuristicTypeRadioGroupPane();
        meshTypeSettingsPane = new MeshTypeSettingsPane();
        getChildren().add(searchTypeRadioGroupPane);
        getChildren().add(heuristicTypeRadioGroupPane);
        getChildren().add(meshTypeSettingsPane);
    }

    private VBox createSearchTypeRadioGroupPane() {
        VBox searchTypeRadioGroupPane = new VBox();
        final ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton aStarChoice = new RadioButton("A*");
        aStarChoice.setToggleGroup(toggleGroup);
        aStarChoice.setSelected(true);
        RadioButton idaStarChoice = new RadioButton("IDA*");
        idaStarChoice.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (!toggle.equals(t1)) {
                    RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                    switch (selectedRadioButton.getText()) {
                        case "A*":
                            gameModel.setSearchType(SearchType.A_STAR);
                            break;
                        case "IDA*":
                            gameModel.setSearchType(SearchType.IDA_STAR);
                            break;
                    }
                }
            }
        });
        searchTypeRadioGroupPane.getChildren().add(aStarChoice);
        searchTypeRadioGroupPane.getChildren().add(idaStarChoice);

        return searchTypeRadioGroupPane;
    }

    private VBox createHeuristicTypeRadioGroupPane() {
        VBox heuristicTypeRadioGroupPane = new VBox();
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton unsortedBlocksChoice = new RadioButton("Nieposegregowane bloczki");
        unsortedBlocksChoice.setToggleGroup(toggleGroup);
        unsortedBlocksChoice.setSelected(true);
        RadioButton manhattanDistanceChoice = new RadioButton("Metryka Manhattan");
        manhattanDistanceChoice.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                switch (selectedRadioButton.getText()) {
                    case "Nieposegregowane bloczki":
                        gameModel.setHeuristicType(HeuristicType.UNORDERED_BLOCKS);
                        break;
                    case "Metryka Manhattan":
                        gameModel.setHeuristicType(HeuristicType.MANHATTAN_DISTANCE);
                        break;
                }
            }
        });
        heuristicTypeRadioGroupPane.getChildren().add(unsortedBlocksChoice);
        heuristicTypeRadioGroupPane.getChildren().add(manhattanDistanceChoice);

        return heuristicTypeRadioGroupPane;
    }

}
