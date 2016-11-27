package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

class SettingsPane extends GridPane {
    private final ToggleGroup toggleGroup = new ToggleGroup();

    SettingsPane() {
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100);
        getColumnConstraints().add(columnConstraints);
        SolveTypeSettingsPane solveTypeSettingsPane = new SolveTypeSettingsPane();
        solveTypeSettingsPane.setMaxWidth(Double.MAX_VALUE);
        GridPane.getHgrow(solveTypeSettingsPane);
        MeshTypeSettingsPane meshTypeSettingsPane = new MeshTypeSettingsPane();
        meshTypeSettingsPane.setMaxWidth(Double.MAX_VALUE);
        LimitsSettingsPane limitsSettingsPane = new LimitsSettingsPane();
        limitsSettingsPane.setMaxWidth(Double.MAX_VALUE);
        VBox boardDomensionRadioGroupPane = createBoardDimensionRadioGroupPane();
        boardDomensionRadioGroupPane.setMaxWidth(Double.MAX_VALUE);
        add(solveTypeSettingsPane,0,0);
        add(meshTypeSettingsPane,0,1);
        add(limitsSettingsPane,0,2);
        add(boardDomensionRadioGroupPane,0,3);
    }

    private VBox createBoardDimensionRadioGroupPane() {
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
        RadioButton x3Choice = new RadioButton(x3Dimension);
        x3Choice.setToggleGroup(toggleGroup);
        RadioButton x4Choice = new RadioButton(x4Dimension);
        x4Choice.setToggleGroup(toggleGroup);
        RadioButton x5Choice = new RadioButton(x5Dimension);
        x5Choice.setToggleGroup(toggleGroup);

        boardDimensionRadioGroupPane.add(x3Choice,0,0);
        GridPane.setHalignment(x5Choice, HPos.CENTER);
        boardDimensionRadioGroupPane.add(x4Choice,1,0);
        GridPane.setHalignment(x4Choice, HPos.CENTER);
        boardDimensionRadioGroupPane.add(x5Choice,2,0);
        GridPane.setHalignment(x5Choice, HPos.CENTER);
        x3Choice.setSelected(false);
        x3Choice.setSelected(true);
        Label dimensionInfoLabel = new Label("Wymiar układanki:");

        VBox mainPane = new VBox();
        mainPane.setPadding(new Insets(5));
        mainPane.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(dimensionInfoLabel,
                boardDimensionRadioGroupPane);

        return mainPane;
    }

    ToggleGroup getToggleGroup() {
        return toggleGroup;
    }
}
